package io.gzy.java.base.knowledge.design.pattern.iterator.restaurant;

/**
 * description: MenuTestDrive
 * date: 2021/4/6 5:40 下午
 *
 * @author: guizhenyu
 */
public class MenuTestDrive {

    public static void main(String[] args) {
        BZMenu bzMenu = new BZMenu();
        SXMenu sxMenu = new SXMenu();
        Waitress waitress = new Waitress(bzMenu, sxMenu);
        waitress.printMenu();
    }
}
