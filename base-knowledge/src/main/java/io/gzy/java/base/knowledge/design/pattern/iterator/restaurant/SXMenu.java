package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

import java.util.Iterator;

/**
 * description: SXMenu
 * date: 2021/4/6 5:29 下午
 *
 * @author: guizhenyu
 */
public class SXMenu implements Menu{

    static final int MAX_ITEMS = 4;
    int indexOfItems = 0;
    MenuItem[] menuItems;

    /**
     * 通过构造函数，在菜单里添加一些菜品
     */
    public SXMenu(){
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("青椒肉丝盖浇饭",10.0);
        addItem("西红柿鸡蛋盖浇饭",10.0);
        addItem("牛肉土豆煲仔饭",15.0);
        addItem("金针菇煲仔饭",10.0);
    }

    public void addItem(String name, double price){
        MenuItem menuItem = new MenuItem(name, price);

        if(indexOfItems >= MAX_ITEMS){
            System.out.println("菜单已满！");
        }else {
            menuItems[indexOfItems] = menuItem;
            indexOfItems ++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new SXMenuIterator(menuItems);    }
}
