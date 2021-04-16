package leetcode;

public class LeetCode5 {
    public String longestPalindrome(String s) {
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

    public boolean check(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

}