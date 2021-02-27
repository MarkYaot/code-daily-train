package base;

import com.google.common.base.Optional;

/**
 * Optional<T>可包含一个非空T的引用（代表存在），也可不包含任何内容（代表不存在），避免了使用null产生的歧义
 */
public class AvoidNull {
    public static void main(String[] args) {
        Optional number5 = Optional.of(5);
        System.out.println(number5.isPresent());
        System.out.println(number5.get());
        System.out.println(number5.asSet());
        System.out.println(Optional.absent());
    }
}
