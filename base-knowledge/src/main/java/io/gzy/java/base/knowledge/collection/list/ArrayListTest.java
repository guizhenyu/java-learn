package io.gzy.java.base.knowledge.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * description: ArrayListTest
 * date: 2021/1/15 4:39 下午
 * author: guizhenyu
 */
public class ArrayListTest {

//    public static void main(String[] args) {
//
//        List<Object> list = new ArrayList<>();
//        list.add("23");
//        list.add(12);
//        list.add("sdg");
//        for (Object o:
//             list) {
//            o = 134;
//
//        }

//        Iterator<Object> iterator = list.iterator();
//        while(iterator.hasNext()){
////            iterator.
//            Object o = iterator.next();
//            if(o instanceof String && ((String)o).equals("12")){
//                iterator.remove();
//            }
//        }

//    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        int index = 0;
        for (int num : nums){
            if (num != 0){
                nums[index++] = num;
            }
        }
        while (index < nums.length){
            nums[index ++] = 0;
        }
        System.out.println(nums);
    }
}
