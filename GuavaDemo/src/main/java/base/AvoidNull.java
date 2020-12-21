package base;

import com.google.common.base.Optional;

public class AvoidNull {
    public static void main(String[] args) {
        Optional number5 = Optional.of(5);
        System.out.println(number5.isPresent());
        System.out.println(number5.get());
        System.out.println(number5.asSet());
        System.out.println(Optional.absent());
    }
}
