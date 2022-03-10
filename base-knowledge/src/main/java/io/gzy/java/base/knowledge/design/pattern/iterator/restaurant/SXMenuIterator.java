package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

import java.util.Iterator;

/**
 * description: SXMenuIterator
 * date: 2021/4/6 5:30 下午
 *
 * @author: guizhenyu
 */
public class SXMenuIterator implements Iterator {
    MenuItem[] menuItems;
    int position = 0;

    /**
     * 通过构造函数实例化存储沙县信息的菜单数组
     *
     * @param menuItems
     */
    public SXMenuIterator(MenuItem[] menuItems){
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position += 1;
        return menuItem;
    }
}
