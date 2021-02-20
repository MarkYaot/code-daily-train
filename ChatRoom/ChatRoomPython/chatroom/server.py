#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/19 0:31
# @Author : Czm
# @File : server.py
# @Software: PyCharm
import threading
import socket

conn_list = []


def receive_data(c, a):
    while True:
        content = c.recv(1024).decode("utf-8")
        msg = "%s says:%s" % (a, content)
        for client in conn_list:
            client["conn"].send(msg.encode("utf-8"))
        if content == "exit\n":
            break


if __name__ == "__main__":
    s = socket.socket()
    s.bind(("127.0.0.1", 8080))
    s.listen()

    while True:
        (conn, addr) = s.accept()
        conn_list.append({"conn": conn, "addr": addr})
        threading.Thread(target=receive_data, args=(conn, addr)).start()
