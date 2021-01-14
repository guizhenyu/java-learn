package io.gzy.java.concurrency.service;

import io.gzy.java.concurrency.entity.User;

/**
 * description: ValuePass
 * date: 2021/1/7 9:33 上午
 * author: guizhenyu
 */
public class ValuePass {

   private final static String MEEE = "sdfds";


    public static void main(String[] args) {
        User user = new User("张海国","景德镇");
//        pass(user);
//        System.out.println("user info :" + user.toString());
        System.out.println(Float.MIN_VALUE);

        StringBuilder stringBuilder = new StringBuilder();

        new StringBuffer();
//        passString(MEEE);
//        System.out.println(MEEE);
    }
    
    public static void pass(User user){
         user = new User("guizhenyu","黄桥");
        System.out.println("user info :" + user.toString());
    }
    public static void passString(String str){
        str = "sdf";
        System.out.println("user info :" + str);
    }
}
