package collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * MultiSet可以方便的记录每个元素在集合中出现的次数
 */
public class UseMultiset {
    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("czm");
        multiset.add("czm");
        multiset.add("lxl");

        System.out.println(multiset.elementSet());
        System.out.println(multiset.count("czm"));
    }
}
