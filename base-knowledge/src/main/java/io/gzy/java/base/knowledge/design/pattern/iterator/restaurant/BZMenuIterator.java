package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

import java.util.Iterator;
import java.util.List;

/**
 * description: BZMenuIterator
 * date: 2021/4/6 5:26 下午
 *
 * @author: guizhenyu
 */
public class BZMenuIterator implements Iterator {
    List<MenuItem> menuItems;

    int position = 0;


    /**
     * 通过构造函数，实例化存储包子信息的菜单 List 集合
     * 虽然可以直接使用 Iterator 来遍历集合，为了更好理解迭代器模式，这里自己手写一个迭代器
     *
     * @param menuItems
     */
    public BZMenuIterator(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.size() || menuItems.get(position) == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems.get(position);
        position += 1;
        return menuItem;
    }
}
