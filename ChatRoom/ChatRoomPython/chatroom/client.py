#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/19 0:31
# @Author : Czm
# @File : client.py
# @Software: PyCharm
import socket
import threading
import sys

is_running = True


def receive_data(c):
    while is_running:
        content = c.recv(1024).decode("utf-8")
        if content.find("exit") != -1:
            c.close()
            break
        print(content)


def send_data(c):
    while True:
        content = sys.stdin.readline()
        c.send(content.encode("utf-8"))
        if content == 'exit\n':
            break


if __name__ == "__main__":
    conn = socket.socket()
    conn.connect(("127.0.0.1", 8080))

    threading.Thread(target=receive_data, args=(conn,)).start()
    threading.Thread(target=send_data, args=(conn,)).start()
