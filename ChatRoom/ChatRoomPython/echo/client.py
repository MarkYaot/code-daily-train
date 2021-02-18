#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/18 23:14
# @Author : Czm
# @File : client.py
# @Software: PyCharm

"""
echo客户端代码，发送Hello World至服务端
"""

import socket

if __name__ == '__main__':
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect(("127.0.0.1", 8080))
        s.sendall(b'Hello World!')
        data = s.recv(1024)

    print(repr(data))