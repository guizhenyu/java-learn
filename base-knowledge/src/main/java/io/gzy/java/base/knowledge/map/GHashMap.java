//package io.gzy.java.base.knowledge.map;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.AbstractMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
///**
// * description: GHashMap
// * date: 2021/1/9 10:44 下午
// * author: guizhenyu
// */
//public class GHashMap<K, V> extends AbstractMap<K, V>
//    implements Map<K, V>, Cloneable, Serializable {
//    private static final long serialVersionUID = 362498820763181263L;
//    public Set<Entry<K, V>> entrySet() {
//        return null;
//    }
//
//    /**
//     * 默认初始容量（指桶）
//     */
//    static final int DEFAULT_INITIAL_CAPCITY = 1 << 4;
//
//    /**
//     * 最大容量
//     */
//    static final int MAXMIUM_CAPCITY = 1 << 30;
//
//    /**
//     * 初始默认的加载因子，为什么是0.75
//     */
//    static final float DEFAULT_LOAD_FACTORY = 0.75f;
//
//    /**
//     * 链表转红黑树的阈值，涉及到泊松分布函数得到的，这样，数据比较离散分布
//     */
//    static final int TREEIFY_THRESHOLD = 8;
//
//    /**
//     * 红黑树转链表的阈值
//     */
//    static final int UNTHREEFY_THRESHOLD = 6;
//
//    /**
//     *  链表转红黑树的第二个阈值条件，就是MAP容量是64
//     */
//    static final int MIN_THREEFY_CAPCITY = 64;
//
//    static class Node<K, V> implements Map.Entry<K, V>{
//        final int hash;
//        final K key;
//        V value;
//        Node<K, V> next;
//
//        Node(int hash, K key, V value, Node next){
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//
//
//        @Override
//        public K getKey() {
//            return key;
//        }
//
//        @Override
//        public V getValue() {
//            return value;
//        }
//        @Override
//        public final String toString(){
//            return key + "=" + value;
//        }
//
//        @Override
//        public V setValue(V newValue) {
//           V oldValue = value;
//           value = newValue;
//            return oldValue;
//        }
//
//
//        @Override
//        public final int hashCode(){
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        @Override
//        public final boolean equals(Object o){
//            if (o == this){
//                return true;
//            }
//
//            if (o instanceof Map.Entry){
//                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//                if (Objects.equals(key, ((Entry<?, ?>) o).getKey()) &&
//                    Objects.equals(value, e.getValue())){
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//
//
//    /*------------------- Fields --------------------*/
//
//    /**
//     * HashMap的数组
//     */
//    transient Node<K, V>[] table;
//
//    /**
//     *
//     */
//    transient Set<Map.Entry<K, V>> entrySet;
//
//    /**
//     *  key-value 的大小
//     */
//    transient int size;
//
//
//    /**
//     * 记录被修改的次数
//     */
//    transient int modCount;
//
//    /**
//     *
//     */
//    int threshold;
//
//    /**
//     * 加载因子
//     */
//    final float loadFactor;
//
//    /**
//     *  Entry是模仿的LinkedHashMap.Entry
//      * @param <K>
//     * @param <V>
//     */
//    static class Entry<K,V> extends Node<K,V> {
//        Entry<K,V> before, after;
//        Entry(int hash, K key, V value, Node<K,V> next) {
//            super(hash, key, value, next);
//        }
//    }
//
//    /*-------------------------------------------------------------*/
//    // tree bins
//    // 首先讲一下 红黑树的规则：
//    // 1. 根节点是黑色
//    // 2. 每个叶子节点都是null
//    // 3. 任何两个相邻的节点都不能同时为红色
//    // 4. 每个节点， 从该节点到达其子节点的所有路径，都包含相同数目的黑色节点；
//
//    static final class TreeNode<K, V> extends Entry<K, V>{
//        TreeNode<K, V> parent;
//        TreeNode<K, V> left;
//        TreeNode<K, V> right;
//        TreeNode<K, V> prev;
//        boolean red;
//        TreeNode(int hash, K key, V val, Node<K, V> next){
//            super(hash, key, val, next);
//        }
//
//
//        public Node<K,V> putTreeVal(GHashMap<K, V> kvgHashMap, Node<K, V>[] tab, int hash, K key, V value) {
//
//
//        }
//
//        public void split(GHashMap<K, V> kvgHashMap, Node<K, V>[] newTab, int j, int oldCap) {
//        }
//
//
//        /*  ------------------------------------------------------------ */
//        // 左旋转
//        static <K, V> TreeNode<K, V>  rotateLeft(TreeNode<K, V> root,
//                                                 TreeNode<K, V> p){
//            // 左旋转就是，把要旋转的节点p点变成他右节点pr的左节点，
//            // 第一步 把pr的左节点prl变成p的左节点pl
//            // 第二步 判断 p是不是根节点，如果是根节点，把pr设为
//            TreeNode<K, V> r, pp, rl;
//            if(p != null && (r = p.right) != null){
//
//                if ((rl = p.right = r.left) != null ){
//                    rl.parent = p;
//                }
//
//                if ((pp = r.parent = p.parent) == null){
//                    (root = r).red = false;
//                }else if(pp.left == p){
//                    pp.left = r;
//                }else {
//                    pp.right = r;
//                }
//                r.left = p;
//                p.parent = r;
//            }
//
//            return root;
//        }
//
//        // 右旋转
//        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root,
//                                                 TreeNode<K, V> p){
//            TreeNode<K, V> l, pp, lr;
//            if(p != null && (l = p.left) != null){
//                if ((lr = p.right = l.right) != null){
//                    lr.parent = p;
//                }
//                if ((pp = l.parent = p.parent) == null){
//                    (root = l).red = false;
//                }else if(pp.right == p){
//                    pp.right = l;
//                }else {
//                    pp.left = l;
//                }
//
//
//                l.right = p;
//                p.parent = l;
//
//            }
//            return root;
//
//        }
//    }
//
//
//    /**
//     * 计算key的hash
//     * @param key
//     * @return
//     */
//    static final int hash(Object key){
//        int h;
//        return (key == null)? 0:(h = key.hashCode()) ^ (h >> 16);
//    }
//
//    /**
//     * 判断对象是否实现了Comparable, 如果是，就返回这个对象的class对象，否则是null
//     * @param x
//     * @return
//     */
//    static Class<?> comparableClassFor(Object x) {
//        if (x instanceof Comparable) {
//            // ParameterizedType 泛型类型
//            Class<?> c;
//            Type[] ts, as;
//            Type t;
//            ParameterizedType p;
//            if ((c = x.getClass()) == String.class) {
//                return c;
//            }
//            if ((ts = x.getClass().getGenericInterfaces()) != null) {
//                for (int i = 0; i < ts.length; ++i) {
//                    if (((t = ts[i]) instanceof ParameterizedType) &&
//                            ((p = (ParameterizedType) t).getRawType() ==
//                                    Comparable.class) &&
//                            (as = p.getActualTypeArguments()) != null &&
//                            as.length == 1 && as[0] == c) {
//                        return c;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public V put(K key, V value){
//        return putVal(hash(key), key, value, false, true);
//    }
//
//
//    /**
//     * 根据key,存放value
//     * @param hash key的hash值
//     * @param key
//     * @param value
//     * @param onlyIfAbsent 是否他替换旧值（key）一样时
//     * @param evict
//     * @return
//     */
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
//        Node<K, V>[] tab; Node<K, V> p; int n, i;
//        // table 是空的，初始化
//        if((tab = table) == null || (n = tab.length) == 0){
//            n = (tab = resize()).length;
//        }
//        // i = (n - 1) & hash 效果和 hash % (n - 1)一样， 根据key的hash值计算对应的那个桶
//        if ((p = tab[i = (n - 1) & hash]) == null){
//            // 对应的桶里面为null, 新建一个链表
//            tab[i] = newNode(hash, key, value, null);
//        }else {
//
//            Node<K, V> e;
//            K k;
//            // p 不为null, 而且是链表的的第一个数据， 1.8的版本，新的数据是采用尾部插入链表的形式
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k)))) {
//                e = p;
//            } else if (p instanceof TreeNode) {
//                // 对应的是 二叉树结构, 新增二叉树节点
//                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
//            } else {
//                // 还是链表
//                // 但新的key不等于链表的头节点的key，所以遍历整个链表
//                for (int binCount = 0; ; binCount++) {
//                    // 遍历结束，也没找到key值相等的节点，所以直接新建一个节点，放到链表的最后
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) {
//                            // 链表转红黑树的条件，这边是链表数量大于8，
//                            // 其实在treeiFyBin方法中还要再判断tab的length是否大于64，才能真正的进行链表转红黑树的操作哦
//                            treeifyBin(tab, hash);
//                            break;
//                        }
//                    }
//                    // 找到key值相等的节点
//                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        break;
//                    }
//                    p = e;
//                }
//            }
//
//            if (e != null) {
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null) {
//                    e.value = value;
//                }
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold) {
//            resize();
//        }
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//
//
//    /**
//     * 这个是为了给LinkedHashMap留后路，可实现有序性
//     * @param evict
//     */
//    private void afterNodeInsertion(boolean evict) {
//    }
//
//    /**
//     * afterNodeAccess
//     * linkHashMap中的方法，这边暂时不考虑
//     * @param e
//     */
//    private void afterNodeAccess(Node<K,V> e) {
//    }
//
//
//    /**
//     * 链表转红黑树的逻辑
//     * @param tab
//     * @param hash
//     */
//    private void treeifyBin(Node<K,V>[] tab, int hash) {
//    }
//
//    /**
//     * 初始化链表
//     * @param hash
//     * @param key
//     * @param value
//     * @param o
//     * @return
//     */
//    private Node<K,V> newNode(int hash, K key, V value, Node<K, V> next) {
//        return new Node<>(hash, key, value, next);
//    }
//
//    /**
//     * hashMap的扩容
//     * @return
//     */
//    private Node<K,V>[] resize() {
//        Node<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null)? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        //1. 计算新的容量和临界值
//        if (oldCap > 0){
//            if (oldCap >= MAXMIUM_CAPCITY){
//                // 容量超过最大值，不变
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            else if((newCap = oldCap << 1) < MAXMIUM_CAPCITY &&
//            oldCap >= DEFAULT_INITIAL_CAPCITY){
//                // 标记##，其它情况，扩容两倍，并且扩容后的长度要小于最大值，old长度也要大于16
//                newThr = oldThr << 1;
//            }
//        }
//        else if(oldThr > 0){
//            // oldCap == 0,  应该是remove操作
//            newCap = oldThr;
//        }
//        else{
//            // 都等于0，就是初始化
//            newCap = DEFAULT_INITIAL_CAPCITY;
//            newThr = (int) (DEFAULT_INITIAL_CAPCITY * DEFAULT_LOAD_FACTORY);
//        }
//        // 此处的if为上面标记##的补充，也就是初始化时容量小于默认值16的，此时newThr没有赋值
//        if (newThr == 0){
//            float  ft = (float) (newCap * loadFactor);
//            // //判断是否new容量是否大于最大值，临界值是否大于最大值
//            newThr = (newCap < MAXMIUM_CAPCITY && ft < (float)MAXMIUM_CAPCITY ?
//                    (int)ft: Integer.MAX_VALUE);
//        }
//
//
//        //2. 根据新的容量，创建新的tab,并且把旧的tab中的值移到新的tab中
//        threshold = newThr;
//        Node<K, V>[] newTab = new Node[newCap];
//
//        table = newTab;
//        if (oldTab != null){
//            for(int j = 0; j < oldCap; ++j){
//                // 临时变量
//                Node<K, V> e;
//                if ((e = oldTab[j]) != null){
//                    oldTab[j] = null;
//                    if (e.next == null){
//                        newTab[e.hash & (newCap - 1)] = e;
//                    }
//                    else if (e instanceof TreeNode){
//                        ((TreeNode<K, V>)e).split(this, newTab, j, oldCap);
//                    }
//                    else {
//                        Node<K, V> loHead = null, loTail = null;
//                        Node<K, V> hiHead = null, hiTail = null;
//                        Node<K, V> next;
//                        do{
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0){
//                                if (loTail == null){
//                                    loHead = e;
//                                }else{
//                                    loTail.next = e;
//                                }
//                                loTail = e;
//                            }
//                            else{
//                                if (hiTail == null){
//                                    hiHead = e;
//                                }else {
//                                    hiTail.next = e;
//                                }
//                                hiTail = e;
//                            }
//                        }while ((e = next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null){
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//}
