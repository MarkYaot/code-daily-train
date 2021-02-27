#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/26 0:20
# @Author : Czm
# @File : urllib_request.py
# @Software: PyCharm
from urllib import request

with request.urlopen("https://www.baidu.com") as response:
    print(response.status)
    print(response.reason)
    print(response.read())
