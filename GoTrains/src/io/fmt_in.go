//使用fmt读取标准输入

package main

import "fmt"

func main() {
	var (
		name string
		age  int
	)

	fmt.Scanf("%s %d", &name, &age)
	fmt.Printf("name is:%s, age is %d\n", name, age)
}
