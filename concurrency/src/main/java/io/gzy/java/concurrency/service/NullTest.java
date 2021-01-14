package io.gzy.java.concurrency.service;

import java.util.Base64;
import java.util.HashMap;

/**
 * description: NullTest
 * date: 2021/1/6 5:59 下午
 * author: guizhenyu
 */
public class NullTest {

//    public static void main(String[] args) {
//        Base64.Encoder encoder = Base64.getEncoder();
//        String s = (String) null;
//        System.out.println(encoder.encodeToString("桂振宇".getBytes()));
//        System.out.println(encoder.encodeToString("张泽宇".getBytes()));
//        System.out.println(encoder.encodeToString("4546".getBytes()));
//    }
public static void main(String[] args) {
    HashMap<String, String> hashMap = new HashMap<>();

    hashMap.put(null, null);
    hashMap.put("null", "的个梵蒂冈");
    hashMap.put("null", "的个梵蒂冈1");
    hashMap.put("null", "的个梵蒂冈1");
    hashMap.put("null", "的个梵蒂冈2");

    System.out.println(hashMap.size());
}
}
