package io.gzy.java.base.knowledge.design.pattern.decorator;



/**
 * description: SimpleTroll
 * date: 2021/4/6 4:07 下午
 *
 * @author: guizhenyu
 */
public class SimpleTroll implements Troll{



    @Override
    public void attack() {
        System.out.println("The troll tries to grab you!");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        System.out.println("The troll shrieks in horror and runs away!");
    }
}
