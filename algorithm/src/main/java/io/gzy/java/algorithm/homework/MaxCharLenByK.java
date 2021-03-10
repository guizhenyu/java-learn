package io.gzy.java.algorithm.homework;

/**
 * description: MaxCharLenByK
 * 替换后，求最大字符串
 * date: 2021/2/4 1:34 下午
 * author: guizhenyu
 */
public class MaxCharLenByK {
    /**
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
     * 总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
     *
     * 示例 1：
     *
     * 输入：s = "ABAB", k = 2
     * 输出：4
     * 解释：用两个'A'替换为两个'B',反之亦然。
     * 示例 2：
     *
     * 输入：s = "AABABBA", k = 1
     * 输出：4
     * 解释：
     * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
     * 子串 "BBBB" 有最长重复字母, 答案为 4。
     * @param str
     * @param k
     * @return
     */

    public static int characterReplacement(String str, int k){
        int len = str.length();
        if (len < 2){
            return len;
        }

        int left = 0;
        int right = 0;
        int[] freq = new int[26];
        int maxcount = 0;
        int res = 0;

        while (right < len){
            freq[str.charAt(right) - 'A'] ++;
            right ++;
            maxcount = Math.max(maxcount, freq[str.charAt(right) - 'A']);

            if (right - left > maxcount + k){
                freq[str.charAt(left) - 'A'] --;
                left ++;
            }
            res = Math.max(res, right - left);
        }

        return res;

    }
}
