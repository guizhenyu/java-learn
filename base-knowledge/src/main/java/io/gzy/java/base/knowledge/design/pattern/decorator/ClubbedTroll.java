package io.gzy.java.base.knowledge.design.pattern.decorator;


/**
 * description: ClubbedTroll
 * date: 2021/4/6 4:08 下午
 *
 * @author: guizhenyu
 */


public class ClubbedTroll implements Troll{


    private Troll decorated;

    public ClubbedTroll(Troll troll){
        this.decorated = troll;
    }
    @Override
    public void attack() {
        decorated.attack();
        System.out.println("The troll swings at you with a club!");
    }

    @Override
    public int getAttackPower() {
        return decorated.getAttackPower() + 10;
    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
    }
}
