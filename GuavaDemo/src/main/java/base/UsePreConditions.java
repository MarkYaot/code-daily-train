package base;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用PreConditions进行校验，消除if else，抛出带错误信息的对应异常，使代码的意义更为明确，
 */
public class UsePreConditions {
    public static void main(String[] args) {
        someService("abc", 6, new ArrayList<Integer>());
    }

    public static void someService(String arg1, int arg2, List<Integer> arg3) {
        Preconditions.checkNotNull(arg1 != null, "arg1 is null");
        Preconditions.checkArgument(arg2 > 5, "arg2 is less than 5");
        Preconditions.checkState(!arg3.isEmpty(), "arg3 is empty");
    }
}

