package base;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseOrdering {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("czm", 3));
        list.add(new Person("lxl", 2));
        list.add(new Person("test", 1));

        //创建自然顺序规则的Order
        Ordering<Person> ordering = Ordering.natural();
        list.sort(ordering);
        System.out.println(Arrays.toString(list.toArray()));

        //创建反序规则的Order
        ordering = Ordering.natural().reverse();
        list.sort(ordering);
        System.out.println(Arrays.toString(list.toArray()));

        //创建使用toString比较的Order，并获取最大的2项
        System.out.println(Arrays.toString(Ordering.usingToString()
                .greatestOf(list.iterator(), 2).toArray()));


        //使用onResultOf，使用参数Function返回的内容项来排序
        list.sort(Ordering.natural().reverse().onResultOf(new Function<Person, String>() {
            @Override
            public @Nullable String apply(@Nullable Person input) {
                return input.getName();
            }
        }));
        System.out.println(Arrays.toString(list.toArray()));
    }
}