package io.gzy.java.base.knowledge.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * description: CglibTest
 * date: 2021/3/29 3:34 下午
 *
 * @author: guizhenyu
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderServiceImpl.class);
        enhancer.setCallback(new OrderInterceptor());

        OrederService orederService = (OrederService) enhancer.create();
        orederService.order();
    }
}
