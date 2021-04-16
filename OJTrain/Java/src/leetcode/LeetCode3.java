package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode3 {
    public int lengthOfLongestSubstring(String s) {
        int windowBegin;
        int maxWindowSize = 0;

        for (windowBegin = 0; windowBegin < s.length(); windowBegin++) {
            Set<Character> set = new HashSet<>();
            int windowEnd = windowBegin;
            while (windowEnd < s.length()) {
                if (set.contains(s.charAt(windowEnd))) {
                    break;
                }
                set.add(s.charAt(windowEnd++));
            }
            maxWindowSize = Math.max(windowEnd - windowBegin, maxWindowSize);
        }
        return maxWindowSize;
    }
}
