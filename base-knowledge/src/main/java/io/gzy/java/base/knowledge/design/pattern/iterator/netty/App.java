package io.gzy.java.base.knowledge.design.pattern.iterator.netty;

import io.gzy.java.base.knowledge.design.pattern.iterator.netty.bst.BstIterator;
import io.gzy.java.base.knowledge.design.pattern.iterator.netty.bst.TreeNode;
import io.gzy.java.base.knowledge.design.pattern.iterator.netty.list.Item;
import io.gzy.java.base.knowledge.design.pattern.iterator.netty.list.ItemType;
import io.gzy.java.base.knowledge.design.pattern.iterator.netty.list.TreasureChest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author james mu
 * @date 2020/4/18 21:59
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final TreasureChest TREASURE_CHEST = new TreasureChest();

    private static void demonstrateTreasureChestIteratorForType(ItemType itemType) {
        System.out.println();
        System.out.println("------------------------");
        System.out.println("Item Iterator for ItemType " + itemType + ": ");
        Iterator<Item> itemIterator = TREASURE_CHEST.iterator(itemType);
        while (itemIterator.hasNext()) {
            System.out.println(itemIterator.next().toString());
        }
    }

    private static void demonstrateBstIterator() {
        System.out.println("------------------------");
        System.out.println("BST Iterator: ");
        TreeNode<Integer> root = buildIntegerBst();
        BstIterator<Integer> bstIterator = new BstIterator<Integer>(root);
        while (bstIterator.hasNext()) {
            System.out.println("Next node: " + bstIterator.next().getVal());
        }
    }

    private static TreeNode<Integer> buildIntegerBst() {
        TreeNode<Integer> root = new TreeNode<>(8);

        root.insert(3);
        root.insert(10);
        root.insert(1);
        root.insert(6);
        root.insert(14);
        root.insert(4);
        root.insert(7);
        root.insert(13);

        return root;
    }

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        demonstrateTreasureChestIteratorForType(ItemType.RING);
        demonstrateTreasureChestIteratorForType(ItemType.POTION);
        demonstrateTreasureChestIteratorForType(ItemType.WEAPON);
        demonstrateTreasureChestIteratorForType(ItemType.ANY);

        demonstrateBstIterator();
    }
}
