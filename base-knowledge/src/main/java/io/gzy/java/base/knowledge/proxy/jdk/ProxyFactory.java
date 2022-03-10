package io.gzy.java.base.knowledge.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: ProxyFactory
 * date: 2021/3/29 4:36 下午
 *
 * @author: guizhenyu
 */
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("order")){
                            System.out.println("jdk proxy start");
                        }

                        Object returnValue = method.invoke(target, args);

                        if (method.getName().equals("pay")){
                            System.out.println("jdk proxy pay end");
                        }
                        return returnValue;
                    }
                });
    }
}
