package io.gzy.java.base.knowledge.constructor;

import io.gzy.java.base.knowledge.entity.Pencile;

import java.util.HashMap;

/**
 * description: ConstructorClass
 * date: 2021/1/8 10:13 上午
 * author: guizhenyu
 */
public class ConstructorClass {

    public static void main(String[] args){
//        Pencile A=new Pencile();
//        Pencile B=new Pencile();
//        System.out.println( "共创建了"+A.GetCount()+"个实例！" );

        HashMap<Pencile, String> hashMap = new HashMap<Pencile, String>();
        Pencile pencile = new Pencile();
        pencile.setName("hah");

        hashMap.put(pencile, "hah");

        pencile.setName("gdfd");
        hashMap.put(pencile, "sdgfd");

        System.out.println(hashMap.get(pencile));

//        1、每个节点要么是红色，要么是黑色，但根节点永远是黑色的；
//        2、每个红色节点的两个子节点一定都是黑色；
//        3、红色节点不能连续（也即是，红色节点的孩子和父亲都不能是红色）；
//        4、从任一节点到其子树中每个叶子节点的路径都包含相同数量的黑色节点；
//        5、所有的叶节点都是是黑色的（注意这里说叶子节点其实是上图中的 NIL 节点）；

    }
}
