package main

import (
	"fmt"
	"time"
)

func compute(start int, end int, c chan int) {
	t := time.Now().UnixNano()
	sum := 0
	for i := start; i < end; i++ {
		sum += 1
	}
	fmt.Printf("compute sum from %d to %d, cost %d ns\n", start, end, time.Now().UnixNano()-t)
	c <- sum
}

func main() {
	c1 := make(chan int)
	c2 := make(chan int)

	go compute(0, 100000000, c1)
	go compute(100000001, 200000000, c2)
	sum := <-c1
	sum += <-c2
	fmt.Printf("sum is %d", sum)
}
