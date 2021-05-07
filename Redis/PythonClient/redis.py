import socket


class Client:
    is_connected = False
    _socket = None
    _buffer = None
    _host = ""
    _port = -1

    def __init__(self, host, port):
        self._host = host
        self._port = int(port)

    def connect(self):
        if not self.is_connected:
            self._socket = socket.socket()
            ip_port = (str(self._host), self._port)
            self._socket.connect(ip_port)
            self._buffer = bytearray(1024)
            self.is_connected = True

    def set(self, key, val):
        self.connect()
        command_data = "*3\r\n$3\r\nSET\r\n"
        key_data = "$" + str(len(key)) + "\r\n" + key + "\r\n"
        val_data = "$" + str(len(val)) + "\r\n" + val + "\r\n"
        self._socket.sendall((command_data + key_data + val_data).encode('utf8'))

    def get_status_code_reply(self):
        b = self._socket.recv(1)
        if b.decode('utf8') == '+':
            num = self._socket.recv_into(self._buffer)
            if num != -1:
                return self._buffer[:num].decode('utf8')


class Redis:
    _client = None

    def __init__(self, host, port):
        self._client = Client(host, port)

    def set(self, key, val):
        self._client.set(key, val)
        return self._client.get_status_code_reply()


if __name__ == "__main__":
    redis = Redis("127.0.0.1", 6379)
    print(redis.set("name", "czm"), end="")
