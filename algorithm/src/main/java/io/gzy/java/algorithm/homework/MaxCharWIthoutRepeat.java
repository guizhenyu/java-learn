package io.gzy.java.algorithm.homework;

import java.util.HashSet;
import java.util.Set;

/**
 * description: MaxCharWIthoutRepeat
 * date: 2021/2/10 6:12 上午
 * author: guizhenyu
 */
public class MaxCharWIthoutRepeat {


    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        if(StringUtils.){
            return maxLength;
        }
        char[] chars = s.toCharArray();
        Set<Character> set =new HashSet();

        for(int i = 0; i < chars.length; i++){
            if(set.contains(chars[i])){
                maxLength = Math.max(maxLength, set.size());
                set = new HashSet<Character>();
            }
            set.add(chars[i]);
        }

        return maxLength;
    }
}
