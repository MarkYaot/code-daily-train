package org.czm.leetcode;

public class LeetCode5 {
    public static String longestPalindrome(String s) {
        int windowBegin;
        int maxLength = 0;
        String maxString = "";

        for (windowBegin = 0; windowBegin < s.length(); windowBegin++) {
            int windowEnd = windowBegin + 1;
            while (windowEnd <= s.length()) {
                String target = s.substring(windowBegin, windowEnd);
                if (check(target) && target.length() > maxLength) {
                    maxLength = windowEnd - windowBegin;
                    maxString = s.substring(windowBegin, windowEnd);
                }
                windowEnd++;
            }
        }
        return maxString;
    }

    public static boolean check(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("aaaaaaaaaaaaaaaaaa"));
        System.out.println(longestPalindrome("abcd"));
    }
}

/**
 * 解法1：暴力
 * 解法2：暴力（中心扩散法）
 * 解法3：动态规划
 * 解法4：（Manacher）马拉车算法
 */
