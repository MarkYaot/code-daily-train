//使用fmt格式化标准输出

package main

import "fmt"

func main() {
	//显示输出换行符
	fmt.Print("Hello World!", "\n")
	//字符会输出对应十进制表示
	fmt.Print('\n')

	//自动输出换行符
	fmt.Println("Hello World!")

	//格式化输出
	fmt.Printf("%s\n", "Hello World!")
	fmt.Printf("%d\n", 10)
	fmt.Printf("%f\n", 10.2)
}