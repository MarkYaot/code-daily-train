package main

import "fmt"

func strStr(haystack string, needle string) int {
	if needle == "" {
		return 0
	}
	if len(needle) > len(haystack) {
		return -1
	}
	if needle == haystack {
		return 0
	}

	index := -1
	winSize := len(needle)
	for i := 0; i <= len(haystack)-winSize; i++ {
		index = i
		for j := 0; j < winSize; j++ {
			if haystack[i+j] != needle[j] {
				index = -1
				break
			}
		}
		if index != -1 {
			return index
		}
	}
	return index
}

func main() {
	fmt.Println(strStr("hello", "ll"))
}
