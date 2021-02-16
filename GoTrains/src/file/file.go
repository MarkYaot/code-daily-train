package main

import (
	"fmt"
	"io/ioutil"
	"os"
)

func main() {
	name := "d:/test.txt"
	createFile(name)
	writeFile(name)
	readFile(name)
	deleteFile(name)
}

func deleteFile() {

}

func writeFile() {
	ioutil.WriteFile()
}

func readFile(fileName string) {
	file, _ := os.Open(fileName)
	defer file.Close()
	bytes, _ := ioutil.ReadFile(fileName)
	fmt.Print(string(bytes))
}

func createFile(fileName string) {
	file, _ := os.Stat(fileName)
	if file == nil {
		file, _ := os.Create(fileName)
		fmt.Println(file.Name())
	}
}
