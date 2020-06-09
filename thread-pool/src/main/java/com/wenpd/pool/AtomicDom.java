package com.wenpd.pool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类实例
 */
public class AtomicDom {

    private static int n;// 非原子类
    private static AtomicInteger atomicN; // 原子类

    public static void main(String[] args) throws InterruptedException {
        n = 0;
        atomicN = new AtomicInteger(0);
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 1000; i ++){
                    n++;
//                    atomicN.addAndGet(1);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 1000; i ++){
                    n++;
//                    atomicN.addAndGet(1);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("n的最终值："+n);
        System.out.println("atomicN的最终值："+atomicN.get());
    }

}
