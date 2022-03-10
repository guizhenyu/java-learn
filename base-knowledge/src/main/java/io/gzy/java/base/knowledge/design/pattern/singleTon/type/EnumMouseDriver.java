package io.gzy.java.base.knowledge.design.pattern.singleTon.type;

/**
 * 基于枚举的单例实现, Effective Java 中文第二版(Joshua Bloch) p.15
 * 此实现是线程安全的，但是添加任何其他方法及其线程安全是开发人员的责任
 *
 * description: EnumMouseDriver
 * date: 2021/4/7 1:42 下午
 *
 * @author: guizhenyu
 */
public enum EnumMouseDriver {

    INSTANCE;

    @Override
    public String toString(){
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
