package org.czm.leetcode;

public class LeetCode4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                nums3[index] = nums1[i++];
            } else {
                nums3[index] = nums2[j++];
            }
            index++;
        }
        for (; i < nums1.length; i++) {
            nums3[index++] = nums1[i];
        }
        for (; j < nums2.length; j++) {
            nums3[index++] = nums2[j];
        }

        if (index % 2 == 0) {
            return (double) (nums3[index / 2] + nums3[index / 2 - 1]) / 2;
        } else {
            return nums3[index / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
