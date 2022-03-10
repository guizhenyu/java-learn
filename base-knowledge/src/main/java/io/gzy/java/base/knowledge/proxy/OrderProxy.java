package io.gzy.java.base.knowledge.proxy;

import io.gzy.java.base.knowledge.proxy.cglib.OrederService;

/**
 * description: OrderProxy 静态代理
 * date: 2021/3/29 4:24 下午
 *
 * @author: guizhenyu
 */

public class OrderProxy implements OrederService {

    private OrederService orederService;

    public OrderProxy(OrederService orederService){
        this.orederService = orederService;
    }
    @Override
    public void order() {
        System.out.println("静态代理 开始");
        orederService.order();
        System.out.println("静态代理 结束");
    }

    @Override
    public void pay() {
        System.out.println("静态代理 开始");
        orederService.pay();
        System.out.println("静态代理 结束");
    }
}
