package main

import (
	"container/list"
	"fmt"
	"net"
)

var serverQuit chan bool

func forwardData(conn net.Conn, connList *list.List) {
	for {
		var buff = make([]byte, 1024)
		n, err := conn.Read(buff)
		if err != nil {
			serverQuit <- true
			fmt.Println("read error! socket exit!")
			return
		}
		if n != 0 {
			fmt.Println(conn.RemoteAddr(), string(buff[:n]))
			for conn := connList.Back(); conn != nil; conn = conn.Prev() {
				_, err := conn.Value.(net.Conn).Write(buff)
				if err != nil {
					serverQuit <- true
					fmt.Println("write error! socket exit!")
					return
				}
			}
		}
	}
}

func main() {
	ln, err := net.Listen("tcp", ":8090")
	if err != nil {
		fmt.Println("listen error! server exit")
		return
	}

	connList := list.New()
	for {
		conn, err := ln.Accept()
		if err != nil {
			serverQuit <- true
			fmt.Println("error! server exit!")
			return
		}
		connList.PushBack(conn)
		go forwardData(conn, connList)
	}
}
