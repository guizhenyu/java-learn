package io.gzy.java.base.knowledge.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: ConcurrentMapTest
 * date: 2021/1/15 2:21 下午
 * author: guizhenyu
 */
public class ConcurrentMapTest {

    public static void main(String[] args) {
        System.out.println("start");
        Map<String, Integer> map = new ConcurrentHashMap<>(16);

//        map.computeIfAbsent("AaAa", key ->{
//            return map.computeIfAbsent("BBBB", key2->42);
//        });
        map.put("AaAAa", 7);
        map.computeIfAbsent("AaAAa", k-> new Integer(9) );
        System.out.println(map.get("AaAAa"));
        System.out.println("end");
    }
}
