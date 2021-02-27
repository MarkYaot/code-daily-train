package collections;

import com.google.common.collect.ImmutableSet;

/**
 * 不可变集合
 */
public class UseImmutableCollections {
    public static void main(String[] args) {
        ImmutableSet<String> immutableSet = ImmutableSet.of("czm", "lxl", "test");
        System.out.println(immutableSet.asList());

        immutableSet = ImmutableSet.copyOf(new String[]{"czm", "lxl", "test"});
        System.out.println(immutableSet.asList());

        //不允许修改
        immutableSet.add("aaa");
        immutableSet.remove("bbb");
    }
}
