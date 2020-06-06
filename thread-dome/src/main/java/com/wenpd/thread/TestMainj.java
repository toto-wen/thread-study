package com.wenpd.thread;

import com.wenpd.thread.callable.MyCallAble;
import com.wenpd.thread.domo.CountNum;

import java.util.concurrent.*;

public class TestMainj {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final CountNum countNum = new CountNum();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                countNum.one();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                countNum.two();
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                countNum.three();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
