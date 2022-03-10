package io.gzy.java.base.knowledge.design.pattern.decorator;

/**
 * description: DecoratorTest
 * date: 2021/4/6 4:12 下午
 * 装饰器的模式
 *   1. 定义统一接口Troll
 *   2. 类SimpleTroll实现Troll(作为被装饰的类)
 *   3. 类ClubbedTroll实现Troll(执行装饰的动作),该类有一个私有成员变量Troll， 通过有参构造函数传入需要装饰的Troll实现类
 *
 *
 *
 * @author: guizhenyu
 */
public class DecoratorTest {


    public static void main(String[] args) {
        // simple troll
        System.out.println("A simple looking troll approaches.");
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();
        System.out.println("Simple troll power ." + troll.getAttackPower());

        // change the behavior of the simple troll by adding a decorator
        System.out.println("A troll with huge club surprises you.");
        ClubbedTroll clubbedTroll = new ClubbedTroll(troll);
        clubbedTroll.attack();
        clubbedTroll.fleeBattle();
        System.out.println("Clubbed troll power " + clubbedTroll.getAttackPower());
    }
}
