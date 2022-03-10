package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

import java.util.Iterator;

/**
 * description: Menu
 * date: 2021/4/6 5:21 下午
 *
 * @author: guizhenyu
 */
public interface Menu {

    Iterator<MenuItem> createIterator();
}
