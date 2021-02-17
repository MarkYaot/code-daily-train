//原生io练习，实现自定义的Reader和Writer

package main

import (
	"fmt"
	"io"
	"os"
)

/**
定制Reader，将所有字符转换为小写
 */
type MyReader struct {
	reader io.Reader
}

func (reader *MyReader) Read(p []byte) (n int, err error) {
	n, err = reader.reader.Read(p)
	if err != nil {
		return n, err
	}
	for i := 0; i < n; i++ {
		c := p[i]
		if  c <= 'Z' && c >= 'A' {
			p[i] = c + 32
		}
	}
	return n, nil
}

/**
定制Writer，将所有字符转换为大写
 */
type MyWriter struct {
	writer io.Writer
}

func (writer *MyWriter) Write(p []byte) (n int, err error) {
	for i := 0; i < len(p); i++ {
		c := p[i]
		if c <= 'z' && c >= 'a' {
			p[i] = c - 32
		}
	}
	n, err = writer.writer.Write(p)
	if err != nil {
		return n, err
	}
	return n, nil
}

func main() {
	//创建文件
	name := "d:/test.txt"
	os.Remove(name)
	os.Create(name)

	//将小写字母写入文件
	file, _ := os.OpenFile(name, os.O_WRONLY, 0666)
	writer := MyWriter{file}
	writer.Write([]byte("abcd"))

	//读取文件
	file, _ = os.Open(name)
	bytes := make([]byte, 4)
	reader := MyReader{file}
	reader.Read(bytes)
	fmt.Println(string(bytes))
}
