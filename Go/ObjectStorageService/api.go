package main

import (
	"encoding/json"
	"fmt"
	"github.com/go-martini/martini"
	"net/http"
)

type apiServer struct {
	server *Server
}

func NewApiServer(s *Server) http.Handler {
	api := &apiServer{server: s}
	m := martini.Classic()
	m.Get("/:bucket", api.ListBucket)
	return m
}

func (s *apiServer) ListBucket(params martini.Params) string {
	m1 := make(map[string][]string)
	objectList, _ := s.server.ListBucket(params["bucket"])
	m1["object_list"] = objectList
	fmt.Println(objectList)
	response, _ := json.MarshalIndent(m1, "", "    ")
	return string(response)
}
