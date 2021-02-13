#!/bin/bash

export GOPATH=/c/Users/c00567567/Desktop/Code/GoTrains
export GOBIN=/c/Users/c00567567/Desktop/Code/GoTrains/bin

cd /c/Users/c00567567/Desktop/Code/GoTrains/src/main
go install
cd /c/Users/c00567567/Desktop/Code/GoTrains/src/hello
go install
cd /c/Users/c00567567/Desktop/Code/GoTrains/src/utils
go install