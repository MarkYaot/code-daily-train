package base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class UseObjects {
    public static void main(String[] args) {
        //Objects equals方法在比较时不用考虑null
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
        String a = new String("a.a.a");
        a.replaceAll("/.", "");
        System.out.println(a);

        System.out.println(new Person().hashCode());
        System.out.println(new Person().toString());
    }
}
