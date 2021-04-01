#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/3/29 0:42
# @Author : Czm
# @File : main.py.py
# @Software: PyCharm

"""
The entrance of Program
"""
from src.server import HttpServer

if __name__ == "__main__":
    server = HttpServer()
    server.start()
