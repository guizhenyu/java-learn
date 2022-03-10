package io.gzy.java.base.knowledge.proxy.jdk;

import io.gzy.java.base.knowledge.proxy.cglib.OrderServiceImpl;
import io.gzy.java.base.knowledge.proxy.cglib.OrederService;

/**
 * description: JDKProxy
 * date: 2021/3/29 4:40 下午
 *
 * @author: guizhenyu
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        OrederService orederService = new OrderServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(orederService);

        OrederService proxy = (OrederService)proxyFactory.getProxyInstance();
        System.out.println(proxy.getClass());

        proxy.order();

        proxy.pay();
    }
}
