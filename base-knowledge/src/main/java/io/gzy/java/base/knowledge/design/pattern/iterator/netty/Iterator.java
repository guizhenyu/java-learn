package io.gzy.java.base.knowledge.design.pattern.iterator.netty;

/**
 * description: Iterator
 * date: 2021/4/6 6:07 下午
 *
 * @author: guizhenyu
 */
public interface Iterator<T> {

    boolean hasNext();

    T next();
}
