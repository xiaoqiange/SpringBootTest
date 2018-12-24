package com.cn.controller;

public class MyRunnable implements Runnable{
    private int i=100;
    @Override
    public synchronized void run() {
        if(i>0)
            System.out.println(Thread.currentThread().getName()+"售出一张票，剩余票数："+ (i=i-1));
        else
            return;
    }
}   
