package main

import "fmt"

func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
	min := int(int32(^uint32(0) >> 1))
	minStr := ""
	for i := 0; i < len(strs); i++ {
		if strs[i] == "" {
			return ""
		}
		if min > len(strs[i]) {
			min = len(strs[i])
			minStr = strs[i]
		}
	}
	for i := 0; i < min; i++ {
		c := strs[0][i]
		for j := 1; j < len(strs); j++ {
			if c != strs[j][i] {
				return strs[0][:i]
			}
		}
	}
	return minStr
}

func main() {
	strs := []string{"ab", "a"}
	fmt.Println(longestCommonPrefix(strs))
}
