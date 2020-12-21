package org.czm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode6 {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<List<Character>> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            lists.add(new ArrayList<>());
        }
        boolean isDown = true;
        int currentLine = 1;
        for (int i = 0; i < s.length(); i++) {
            lists.get(currentLine - 1).add(s.charAt(i));
            if (isDown) {
                currentLine += 1;
            } else {
                currentLine -= 1;
            }
            if ((i + 1) % (numRows - 1) == 0) {
                isDown = !isDown;
            }
        }

        StringBuilder result = new StringBuilder();
        lists.forEach(list -> {
            list.forEach(c -> {
                result.append(c);
            });
        });
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("A", 1));
    }
}
