class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        max_win_size = 0
        for i in range(0, len(s)):
            j = i
            exist_chars = set()
            while j < len(s):
                if s[j] in exist_chars:
                    break
                exist_chars.add(s[j])
                j += 1
            max_win_size = max(j - i, max_win_size)
        return max_win_size


solution = Solution()
print(solution.lengthOfLongestSubstring(" "))
