package main

func main() {
	server := &Server{StoreAddr: "0.0.0.0:9000", DataPath: "C:/Users/86134/Desktop"}
	server.start()
}
