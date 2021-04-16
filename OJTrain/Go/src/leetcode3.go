package main

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func lengthOfLongestSubstring(s string) int {
	maxWinSize := 0
	for i := 0; i < len(s); i++ {
		existChars := make(map[byte]bool)
		for j := i; j < len(s); {
			if existChars[s[j]] {
				break
			}
			existChars[s[j]] = true
			j += 1
			maxWinSize = max(j-i, maxWinSize)
		}
	}
	return maxWinSize
}
