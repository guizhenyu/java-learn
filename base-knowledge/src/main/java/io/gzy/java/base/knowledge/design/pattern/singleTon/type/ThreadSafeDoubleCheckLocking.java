package io.gzy.java.base.knowledge.design.pattern.singleTon.type;

/**
 * description: ThreadSafeDoubleCheckLocking
 *  双重校验锁实现单例
 * date: 2021/4/7 1:49 下午
 *
 * @author: guizhenyu
 */
public class ThreadSafeDoubleCheckLocking {

    private volatile static ThreadSafeDoubleCheckLocking instance;

    public static ThreadSafeDoubleCheckLocking getInstance(){
        if (null == instance){
            synchronized (ThreadSafeDoubleCheckLocking.class){
                if(null == instance){
                    instance = new ThreadSafeDoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}
