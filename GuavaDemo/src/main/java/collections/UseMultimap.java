package collections;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

/**
 * Multimap满足单key对应value，摆脱Map<K, List<V>>或者Map<K, Set<V>>
 */
public class UseMultimap {
    public static void main(String[] args) {
        //创建时使用builder，HashMap存储key，ArrayList存储val
        ListMultimap<String, Integer> multimap = MultimapBuilder.hashKeys().arrayListValues().build();
        multimap.put("czm", 1);
        multimap.put("czm", 2);
        multimap.put("czm", 3);
        System.out.println(multimap.asMap());

        multimap.remove("czm", 1);
        System.out.println(multimap.asMap());
    }
}
