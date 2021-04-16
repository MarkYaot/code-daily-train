from typing import List


class Solution:
    def __init__(self):
        return

    def twoSum(self, nums: List[int], target: int) -> List[int]:
        size = len(nums)
        for i in range(0, size):
            for j in range(i + 1, size):
                if nums[i] + nums[j] == target:
                    return [i, j]


solution = Solution()
print(solution.twoSum(nums=[2, 7, 11, 15], target=9))
