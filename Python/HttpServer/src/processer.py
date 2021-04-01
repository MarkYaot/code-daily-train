#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/3/30 0:21
# @Author : Czm
# @File : processer.py
# @Software: PyCharm
import logging
import os


class HttpProcessor:
    """
    解析出所有请求头，构造并返回响应
    当前限制：只接受不带参数的get请求
    """

    @classmethod
    def process(cls, conn):
        logging.info("Process started!")
        data = str(conn.recv(1024).decode("utf-8"))
        lines = data.split("\r\n")
        headers = {}
        for line in lines:
            if line == '':
                logging.info("Process succeed!")
                break
            if line.startswith("GET /"):
                headers["get"] = line
                path = line.split(" ")[1]
                path = ("/index.html" if path == '/' else path).replace('/', os.sep)
                path = os.path.abspath(__file__) + os.sep + ".." + os.sep + ".." + os.sep + "res" + path
                if not os.path.exists(path):
                    path = ''
            else:
                break

        logging.info("Send response started!")
        try:
            if path == '':
                # 404错误
                data = "HTTP/1.1 404 Not Found\r\n"
                conn.send(str(data + "\r\n").encode("utf-8"))
            else:
                data = "HTTP/1.1 200 OK\r\n"
                with open(path) as f:
                    body = f.read(1024)
                conn.send(str(data + "\r\n" + body).encode("utf-8"))
        except Exception as e:
            logging.error("Send response failed! error:", str(e))
        finally:
            conn.close()
        logging.info("Send response succeed!")

    @classmethod
    def parse_request(cls, conn):
        return

    @classmethod
    def parse_response(cls, conn):
        return
