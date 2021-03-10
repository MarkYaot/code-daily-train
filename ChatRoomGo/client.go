package main

import (
	"fmt"
	"net"
)

var clientQuit chan bool

func receiveData(conn net.Conn) {
	for {
		var buff = make([]byte, 1024)
		n, err := conn.Read(buff)
		if err != nil {
			clientQuit <- true
			fmt.Println("read error! socket exit!")
			return
		}
		if n != 0 {
			fmt.Println(string(buff[:n]))
		}
	}
}

func sendData(conn net.Conn) {
	for {
		data := ""
		fmt.Scanln(&data)
		_, err := conn.Write([]byte(data))
		if err != nil {
			clientQuit <- true
			fmt.Println("write error! socket exit!")
			return
		}
	}

}

func main() {
	addr, _ := net.ResolveTCPAddr("tcp", "127.0.0.1:8090")
	conn, err := net.DialTCP("tcp", nil, addr)
	if err != nil {
		fmt.Println("error! client exit")
		return
	}
	go receiveData(conn)
	go sendData(conn)
	<-clientQuit
}
