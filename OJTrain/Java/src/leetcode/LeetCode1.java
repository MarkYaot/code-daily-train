package leetcode;

public class LeetCode1 {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = 1;
        boolean flag = true;
        for (i = 0; i < nums.length - 1; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        return new int[]{i, j};
    }
}
