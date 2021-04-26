package main

import (
	"fmt"
	"strconv"
	"strings"
)

func myAtoi(s string) int {
	if s == "" {
		return 0
	}

	s = strings.Trim(s, " ")
	for i := 0; i < len(s); i++ {
		if s[i] != '0' {
			s = s[i:]
			break
		}
	}

	negative := false
	if s[0] == '-' {
		negative = true
		s = s[1:]
	} else if s[0] == '+' {
		negative = false
		s = s[1:]
	} else if !(s[0] <= '9' && s[0] >= '0') {
		return 0
	}

	for i := 0; i < len(s); i++ {
		if !(s[i] <= '9' && s[i] >= '0') {
			s = s[:i]
			break
		}
	}

	maxNegative := "2147483648"
	maxPositive := "2147483647"
	INT_MAX := int32(^uint32(0) >> 1)
	INT_MIN := ^INT_MAX

	if len(s) >= 10 {
		if negative && (s > maxNegative || len(s) > 10) {
			return int(INT_MIN)
		}
		if !negative && (s > maxPositive || len(s) > 10) {
			return int(INT_MAX)
		}
	}

	result, _ := strconv.Atoi(s)
	if negative {
		return result * -1
	} else {
		return result
	}
}

func main() {
	fmt.Println(myAtoi(""))
	fmt.Println(myAtoi("4193 with words"))
	fmt.Println(myAtoi("words and 987"))
	fmt.Println(myAtoi("   -42"))
	fmt.Println(myAtoi("21474836460"))
	fmt.Println(myAtoi("-21474836460"))
	fmt.Println(myAtoi("  0000000000012345678"))
	fmt.Println(myAtoi("00000-42a1234"))
}
