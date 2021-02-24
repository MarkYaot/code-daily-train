#!D:/Programming/Python37/python
# -*- coding: utf-8 -*-
# @Time : 2021/2/24 23:22
# @Author : Czm
# @File : performance_comparison.py
# @Software: PyCharm

import multiprocessing as mp
import threading
import time

num = 5
sum = 1000000000
unit = int(sum / num)


def compute(begin, end):
    length = begin;
    for i in range(begin, end):
        length += 1


def threading_test():
    for i in range(0, num):
        threading.Thread(target=compute(i * unit, (i + 1) * unit)).start()


def multiprocessing_test():
    mp.set_start_method('spawn')
    for i in range(0, num):
        mp.Process(target=compute(i * unit, (i + 1) * unit)).start()


if __name__ == "__main__":
    begin_time = time.time()
    print(begin_time)
    # threading_test()
    multiprocessing_test()
    end_time = time.time()
    print(end_time)
    print("cost %f ms" % (float(end_time) - float(begin_time)))
