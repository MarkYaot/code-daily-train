//os包练习：文件操作，进程操作

package main

import (
	"fmt"
	"os"
)

func main() {
	fileOperation()
}

//文件操作
func fileOperation() {
	//创建并写入文件
	name := "d:/test.txt"
	file, _ := os.OpenFile(name, os.O_RDWR, 0666)
	file.Write(([]byte)("abcd"))

	//读取文件
	file, _ = os.Open(name)
	buf := make([]byte, 4)
	file.Read(buf)
	fmt.Println(string(buf))

	//查看文件信息
	fmt.Println(os.Stat(name))

	//删除文件
	os.Remove(name)
}
