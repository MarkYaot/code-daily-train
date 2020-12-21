package org.czm.leetcode;

public class LeetCode9 {
    public static boolean isPalindrome(int x) {
        String num = Integer.valueOf(x).toString();
        int len = num.length();
        for (int i = 0; i < len / 2; i++) {
            if (num.charAt(i) != num.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(121));
    }
}
