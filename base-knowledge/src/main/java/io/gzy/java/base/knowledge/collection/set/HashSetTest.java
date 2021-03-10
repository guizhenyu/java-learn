package io.gzy.java.base.knowledge.collection.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description: HashTest
 * date: 2021/1/16 10:32 上午
 * author: guizhenyu
 */
public class HashSetTest {

    // HashSet 底层是HashMap实现的
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("list");
        list.add("list");
        list.add("set");
        Set<String> set = new HashSet<>();
        set.addAll(list);
        set.add(null);

        for (String str:
             set) {
            System.out.println(str);
        }
    }
}
