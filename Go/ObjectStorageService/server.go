package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net"
	"net/http"
	"sync"
)

type Server struct {
	mu     sync.Mutex
	closed bool
	exit   struct {
		C chan struct{}
	}
	lstore    net.Listener
	StoreAddr string
	DataPath  string
}

// serverStore start storage service
func (s *Server) serverStore() {
	if s.IsClosed() {
		return
	}
	defer s.Close()

	eh := make(chan error, 1)
	go func(l net.Listener) {
		h := http.NewServeMux()
		h.Handle("/", NewApiServer(s))
		hs := &http.Server{Handler: h}
		eh <- hs.Serve(l)
	}(s.lstore)

	select {
	case <-s.exit.C:
		fmt.Printf("server store shutdown\n")
	case err := <-eh:
		fmt.Printf("server store exit on error:%s", err.Error())
	}
}

func (s *Server) Close() error {
	s.mu.Lock()
	defer s.mu.Unlock()
	if s.closed {
		return nil
	}
	s.closed = true
	close(s.exit.C)
	return nil
}

func (s *Server) IsClosed() bool {
	s.mu.Lock()
	defer s.mu.Unlock()
	return s.closed
}

func (s *Server) start() {
	if l, err := net.Listen("tcp", s.StoreAddr); err != nil {
		fmt.Printf(err.Error())
	} else {
		s.lstore = l
	}
	s.serverStore()
}

func (s *Server) ListBucket(bucket string) ([]string, error) {
	files, err := ioutil.ReadDir(s.DataPath + "/" + bucket)
	if err != nil {
		log.Println(err)
		return nil, err
	}
	var fileList []string
	for _, file := range files {
		fileList = append(fileList, file.Name())
	}
	return fileList, nil
}

func main() {
	server := &Server{StoreAddr: "0.0.0.0:9000", DataPath: "c:/Users/86134/Desktop"}
	server.start()
}
