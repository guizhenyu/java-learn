package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * description: BZMenu
 * date: 2021/4/6 5:22 下午
 *
 * @author: guizhenyu
 */
public class BZMenu implements Menu{
    List<MenuItem> menuItems;

    /**
     * 通过构造函数，在菜单里添加一些菜品
     *
     */
    public BZMenu(){
        menuItems = new ArrayList<>();
        addItem("雪菜包",1.0);
        addItem("灌汤包",1.0);
        addItem("韭菜包",1.0);
        addItem("猪肉包",1.0);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new BZMenuIterator(menuItems);
    }

    public void addItem(String name, double price){
        MenuItem menuItem = new MenuItem(name, price);
        menuItems.add(menuItem);
    }
}
