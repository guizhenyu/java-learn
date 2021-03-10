package io.gzy.java.base.knowledge.collection.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * description: LinkedHashSetTest
 * date: 2021/1/16 1:06 下午
 * author: guizhenyu
 */
public class LinkedHashSetTest {

    /**
     *  LinkedHashSet 的底层是LinkedHashMap实现的， 通过看其源码中的构造函数得知，其调用父类的构造函数中有
     *   map = new LinkedHashMap<>(initialCapacity, loadFactor);
     *  这样一段代码
     * @param args
     */
    public static void main(String[] args) {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("LinkedHashSetTest");
        linkedHashSet.add(null);
        System.out.println(linkedHashSet);
    }
}
