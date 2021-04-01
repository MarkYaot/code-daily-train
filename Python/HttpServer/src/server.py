#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/3/29 0:26
# @Author : Czm
# @File : server.py
# @Software: PyCharm

"""
This is a simple HTTP server
Receive HTTP request and send HTTP response
Just for code training myself
"""
import os
import socket
import threading
from configparser import ConfigParser

from src.processer import HttpProcessor


class HttpServer:
    def __init__(self):
        config = ConfigParser()
        path = os.path.abspath(__file__) + os.sep + ".." + os.sep + ".." + os.sep + "res" + os.sep + "server.ini"
        config.read(path, "utf-8")
        self.port = int(config.get("server", "port"))

    def start(self):
        server = socket.socket()
        server.bind(("127.0.0.1", self.port))
        server.listen()

        while True:
            conn, addr = server.accept()
            threading.Thread(target=HttpProcessor.process, args=(conn,)).start()
