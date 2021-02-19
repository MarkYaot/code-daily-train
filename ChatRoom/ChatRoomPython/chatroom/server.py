#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/19 0:31
# @Author : Czm
# @File : server.py
# @Software: PyCharm
import threading
import socket

conn_list = []


def receive_data(conn, addr):
    while True:
        try:
            content = conn.recv(1024).decode("utf-8")
            if content is "exit":
                break
            else:
                msg = "%s says:%s" % (addr, content)
                for client in conn_list:
                    client.send(msg.encode("utf-8"))
        except:
            print("Error!")
    return


if __name__ == "__main__":
    serversocket = socket.socket()
    serversocket.bind(("127.0.0.1", 8080))
    serversocket.listen()

    while True:
        (conn, addr) = serversocket.accept()
        conn_list.append({"conn": conn, "addr": addr})
        threading.Thread(target=receive_data(conn, addr))
