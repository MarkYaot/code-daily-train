package collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * 双向map，同时维护key到val以及val到key的映射
 */
public class UseBiMap {
    public static void main(String[] args) {
        BiMap<String, Integer> map = HashBiMap.create();
        map.put("czm", 1);
        map.put("lxl", 2);

        //key到val
        System.out.println(map.get("czm"));
        //val到key
        System.out.println(map.inverse().get(1));
    }
}
