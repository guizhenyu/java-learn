package io.gzy.java.base.knowledge.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description: LinkedHashMapTest
 * date: 2021/1/16 11:01 上午
 * author: guizhenyu
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
//        Map<String, String> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put("str", "fdsgfd");
//        linkedHashMap.put("str1", "fdsgfd");
//        linkedHashMap.put("str1", "fdsgfd1");
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap.put("Bob",37);
        linkedHashMap.put("Tony",27);
        linkedHashMap.put("Aaron",23);

        System.out.println("------------ Before Access ------------");
        linkedHashMap.forEach( (k,v) -> System.out.println("K : " + k + " V : " + v ) );

        linkedHashMap.put("Tony",23);
        System.out.println("------------ Access \"Tony\" ------------");
        linkedHashMap.forEach( (k,v) -> System.out.println("K : " + k + " V : " + v ) );

        linkedHashMap.get("Bob");
        System.out.println("------------ Access \"Bob\" ------------");
        linkedHashMap.forEach( (k,v) -> System.out.println("K : " + k + " V : " + v ) );
    }
}
