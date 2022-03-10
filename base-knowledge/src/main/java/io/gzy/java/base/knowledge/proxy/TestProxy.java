package io.gzy.java.base.knowledge.proxy;

import io.gzy.java.base.knowledge.proxy.cglib.OrderServiceImpl;

/**
 * description: TestProxy
 * date: 2021/3/29 4:26 下午
 *
 * @author: guizhenyu
 */
public class TestProxy {

    public static void main(String[] args) {

        OrderServiceImpl orderService = new OrderServiceImpl();

        OrderProxy orderProxy = new OrderProxy(orderService);

        orderProxy.order();
    }
}
