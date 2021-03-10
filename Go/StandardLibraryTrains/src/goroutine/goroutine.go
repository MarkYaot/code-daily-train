package main

import (
	"fmt"
	"time"
)

func do_task(n int) {
	fmt.Printf("enter into compute func %d\n", n)
	//模拟耗时任务
	time.Sleep(100 * time.Millisecond)
	fmt.Printf("leave compute func %d\n", n)
}

func main() {
	fmt.Print("enter into main func\n")
	go do_task(1)
	go do_task(2)
	go do_task(3)
	do_task(4)
	fmt.Print("leave main func\n")
}
