package base;

import com.google.common.base.Objects;

/**
 * Objects equals方法在比较时不用考虑null
 */
public class UseObjects {
    public static void main(String[] args) {
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
        String a = new String("a.a.a");
        a.replaceAll("/.", "");
        System.out.println(a);
        System.out.println(Objects.hashCode(1, 2, 3, 4));
    }
}
