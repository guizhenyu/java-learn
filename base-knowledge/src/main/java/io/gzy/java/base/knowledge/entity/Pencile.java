package io.gzy.java.base.knowledge.entity;

/**
 * description: Pencile
 * date: 2021/1/8 10:19 上午
 * author: guizhenyu
 */
public class Pencile {
    public  static int count=0;

    String name ;
    public Pencile(){
        count++;
    }
    public int GetCount(){
        return count;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Pencile.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
