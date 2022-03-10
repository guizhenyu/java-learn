package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

/**
 * description: MenuItem 用来封装菜单食物信息
 * date: 2021/4/6 5:18 下午
 *
 * @author: guizhenyu
 */
public class MenuItem {

    String name;

    double price;

    public MenuItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}
