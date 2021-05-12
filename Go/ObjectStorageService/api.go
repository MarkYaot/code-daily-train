package main

import (
	"encoding/json"
	"github.com/go-martini/martini"
	"github.com/martini-contrib/binding"
	"net/http"
	"os"
)

type uploadObjectRequestBody struct {
	Content string
}

type apiServer struct {
	server *Server
}

func NewApiServer(s *Server) http.Handler {
	api := &apiServer{server: s}
	m := martini.Classic()
	m.Get("/:bucket", api.ListObject)
	m.Get("/:bucket/:object", api.GetObject)
	m.Put("/:bucket/:object", binding.Json(uploadObjectRequestBody{}), api.UploadObject)
	return m
}

func (s *apiServer) ListObject(params martini.Params) (int, string) {
	data := make(map[string][]string)
	objectList, err := s.server.ListObject(params["bucket"])
	if err != nil {
		if os.IsExist(err) {
			return 404, err.Error()
		}
		return 500, err.Error()
	}
	data["object_list"] = objectList
	return 200, jsonResponse(data)
}

func (s *apiServer) GetObject(params martini.Params) (int, string) {
	data, err := s.server.GetObject(params["bucket"], params["object"])
	if err != nil {
		if os.IsExist(err) {
			return 404, err.Error()
		}
		return 500, err.Error()
	}
	return 200, data
}

func (s *apiServer) UploadObject(body uploadObjectRequestBody, params martini.Params, ) int {
	content := body.Content
	s.server.UploadObject(content, params["bucket"], params["object"])
	return 200
}

func jsonResponse(data interface{}) string {
	response, _ := json.MarshalIndent(data, "", "    ")
	return string(response)
}
