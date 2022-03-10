package io.gzy.java.base.knowledge.proxy.cglib;

/**
 * description: OrderServiceImpl
 * date: 2021/3/29 3:41 下午
 *
 * @author: guizhenyu
 */
public class OrderServiceImpl implements OrederService{
    @Override
    public void order() {
        System.out.println("创建订单成功");
    }

    @Override
    public void pay() {
        System.out.println("订单支付成功");
    }
}
