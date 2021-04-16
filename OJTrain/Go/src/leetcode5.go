package main

import "fmt"

func isPalindrome(s string) bool {
	length := len(s) - 1
	for i := 0; i <= length/2; i++ {
		if s[i] != s[length-i] {
			return false
		}
	}
	return true
}

func longestPalindrome(s string) string {
	for winSize := len(s); winSize > 0; winSize-- {
		for i := 0; i < len(s)-winSize+1; i++ {
			end := i + winSize
			if isPalindrome(s[i:end]) {
				return s[i:end]
			}
		}
	}
	return s[:0]
}

func main() {
	fmt.Println(longestPalindrome("babad"))
	fmt.Println(longestPalindrome("cbbd"))
	fmt.Println(longestPalindrome("a"))
	fmt.Println(longestPalindrome(""))
}
