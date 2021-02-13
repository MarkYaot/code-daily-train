package main

import (
	"fmt"
	"hello"
	"utils"
)

func main() {
	fmt.Print(hello.HelloWorld())
	fmt.Print(utils.Calc(1, 2, "+"))
}