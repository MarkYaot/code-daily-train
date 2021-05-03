class RedisValue:
    val_obj = None
    val_type = ""

    def __init__(self, obj):
        self.val_type = obj.__class__.__name__
        self.val_obj = obj


class SimpleString:
    val = ""

    def __init__(self, val):
        self.val = val


class Error:
    val = ""

    def __init__(self, err):
        self.val = err


class Integer:
    val = 0

    def __init__(self, val):
        self.val = int(val)


class BulkString:
    len = 0
    val = ""

    def __init__(self, len, val):
        self.val = val
        self.len = len


class Array:
    len = 0
    val = []

    def __init__(self, len, val):
        self.len = len
        self.val = val


CRLF = "\r\n"


def decode(protocol_body):
    val_type = protocol_body[0]
    if val_type == '+':
        return RedisValue(decode_str(protocol_body)[0])
    elif val_type == '$':
        return RedisValue(decode_bulk_str(protocol_body)[0])
    elif val_type == ':':
        return RedisValue(decode_integer(protocol_body)[0])
    elif val_type == '*':
        return RedisValue(decode_array(protocol_body)[0])
    elif val_type == '-':
        return RedisValue(decode_error(protocol_body)[0])
    else:
        return RedisValue(Error("Unknown value type"))


def decode_str(protocol_body):
    try:
        index = protocol_body.index(CRLF)
        val = protocol_body[1:index]
        protocol_body = protocol_body[index + 2:]
        return SimpleString(val), protocol_body
    except Exception:
        return RedisValue(Error("Decode failed!"))


def decode_error(protocol_body):
    try:
        index = protocol_body.index(CRLF)
        val = protocol_body[1:index]
        left = protocol_body[index + 2:]
        return Error(val), left
    except Exception:
        return RedisValue(Error("Decode failed!"))


def decode_integer(protocol_body):
    try:
        index = protocol_body.index(CRLF)
        val = protocol_body[1:index]
        left = protocol_body[index + 2:]
        return Integer(int(val)), left
    except Exception:
        return RedisValue(Error("Decode failed!"))


def decode_bulk_str(protocol_body):
    try:
        index = protocol_body.index(CRLF, 0)
        len = int(protocol_body[1:index])
        beg = index + 2
        end = protocol_body.index(CRLF, beg)
        val = protocol_body[beg:end]
        left = protocol_body[end + 2:]
        return BulkString(len, val), left
    except Exception:
        return RedisValue(Error("Decode failed!"))


def decode_array(protocol_body):
    try:
        val_list = []
        index = protocol_body.index(CRLF)
        len = int(protocol_body[1:index])
        protocol_body = protocol_body[index + 2:]
        for i in range(0, len):
            val_type = protocol_body[0]
            val = None
            if val_type == '+':
                val, protocol_body = decode_str(protocol_body)
            elif val_type == '-':
                val, protocol_body = decode_error(protocol_body)
            elif val_type == ':':
                val, protocol_body = decode_integer(protocol_body)
            elif val_type == '$':
                val, protocol_body = decode_bulk_str(protocol_body)
            elif val_type == '*':
                val, protocol_body = decode_array(protocol_body)
            if not val:
                return RedisValue(Error("Unknown value type"))
            val_list.append(val)
        protocol_body = protocol_body[2:]
        return Array(len, val_list), protocol_body
    except Exception:
        return RedisValue(Error("Decode failed!"))


def encode(redis_val):
    if redis_val.val_type == SimpleString.__name__:
        return "+%s\r\n" % redis_val.val_obj.val
    elif redis_val.val_type == Error.__name__:
        return "-%s\r\n" % redis_val.val_obj.val
    elif redis_val.val_type == Integer.__name__:
        return ":%s\r\n" % redis_val.val_obj.val
    elif redis_val.val_type == BulkString.__name__:
        len = str(redis_val.val_obj.len)
        return "$%s\r\n%s\r\n" % (len, redis_val.val_obj.val)
    elif redis_val.val_type == Array.__name__:
        len = redis_val.val_obj.len
        val = "*%s\r\n" % str(len)
        for i in range(0, len):
            val += encode(RedisValue(redis_val.val_obj.val[i]))
        return val + "\r\n"


if __name__ == "__main__":
    # parse simple string
    obj = decode("+Hello World!\r\n")
    print(obj.val_obj.val)

    # parse array
    obj = decode("*3\r\n:1\r\n:2\r\n:3\r\n\r\n")
    print("length is:%s" % obj.val_obj.len)
    array = obj.val_obj.val
    print(str(array[0].val) + "," + str(array[1].val) + "," + str(array[2].val))

    # encode array
    string = SimpleString("Hello World!")
    protocol_body = encode(RedisValue(string))
    print(repr(protocol_body))
    integer = Integer("100")
    protocol_body = encode(RedisValue(integer))
    print(repr(protocol_body))
    bulk_string = BulkString(4, "Test")
    protocol_body = encode(RedisValue(bulk_string))
    print(repr(protocol_body))
    val_list = [string, integer, bulk_string]
    array = Array(3, val_list)
    protocol_body = encode(RedisValue(array))
    print(repr(protocol_body))
