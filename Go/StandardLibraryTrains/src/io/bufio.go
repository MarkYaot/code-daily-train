//bufio练习

package main

import (
	"bufio"
	"fmt"
	"strings"
)

func main() {
	//ScannerLines
	scanner := bufio.NewScanner(strings.NewReader("Hello World!\nI'm czm"))
	scanner.Split(bufio.ScanLines)
	for scanner.Scan() {
		fmt.Println(scanner.Text())
	}

	//自定义Scanner
	scanner = bufio.NewScanner(strings.NewReader("Hello World!\nI'm czm"))
	scanner.Split(func(data []byte, atEOF bool) (advance int, token []byte, err error){
		for i := 0; i < len(data); i++ {
			if data[i] == ' ' {
				return i + 1, data[:i], nil
			}
		}
		if !atEOF {
			return 0, nil, nil
		}
		return 0, data, bufio.ErrFinalToken
	})
	for scanner.Scan() {
		fmt.Println(scanner.Text())
	}
}
