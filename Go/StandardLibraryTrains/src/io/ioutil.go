//ioutil练习

package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

func main() {
	//ReadAll
	reader := strings.NewReader("abcd")
	bytes, _ := ioutil.ReadAll(reader)
	fmt.Println(string(bytes))

	//WriteFile
	name := "d:/test.txt"
	os.Create(name)
	ioutil.WriteFile(name, ([]byte)("abcd"), 0644)

	//ReadFile
	content, _ := ioutil.ReadFile(name)
	fmt.Println(string(content))
}