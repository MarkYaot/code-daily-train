#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/18 23:14
# @Author : Czm
# @File : server.py
# @Software: PyCharm

"""
echo服务器代码，打印并发送来自客户端的信息
"""

import socket

if __name__ == '__main__':
    server = socket.socket()
    server.bind(("127.0.0.1", 8080))
    server.listen()

    while True:
        (conn, address) = server.accept()
        data = conn.recv(1024)
        if not data:
            break
        print(repr(data))
        conn.sendall(data)
