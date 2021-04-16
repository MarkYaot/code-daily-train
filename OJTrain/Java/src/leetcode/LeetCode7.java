package leetcode;

public class LeetCode7 {
    public int reverse(int x) {
        int p = x;
        if (x == -2147483648 || x == 2147483647 || x == 0) {
            return 0;
        } else if (p < 0) {
            p = p * -1;
        }
        StringBuilder builder = new StringBuilder();
        while (p != 0) {
            builder.append(p % 10);
            p /= 10;
        }
        String num = builder.toString();
        String max = Integer.valueOf(Integer.MAX_VALUE).toString();
        String min = Integer.valueOf(Integer.MIN_VALUE).toString().substring(1);

        if (x > 0 && num.toString().length() == max.length()) {
            if (num.compareTo(max) > 0) {
                return 0;
            }
        } else if (x < 0 && num.toString().length() == min.length()) {
            if (num.compareTo(min) > 0) {
                return 0;
            }
        }
        return x > 0 ? Integer.parseInt(num) : Integer.parseInt(num) * -1;
    }
}
