package com.wenpd.thread.cyclibarrier;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDome {
    // 定义CyclicBarrier并且设置参与的线程数
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public void startThread(){
        // 打印线程准备启动
        String name = Thread.currentThread().getName();
        System.out.println(name+"线程正在准备...");
        // 调用CyclicBarrier的await方法等待线程全部准备完成
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // 打印线程启动完毕信息
        System.out.println(name+"线程执行完毕"+new Date().getTime());
    }

    public static void main(String[] args){
        final CyclicBarrierDome cyclicBarrierDome = new CyclicBarrierDome();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                cyclicBarrierDome.startThread();
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                cyclicBarrierDome.startThread();
            }
        }, "thread2");

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                cyclicBarrierDome.startThread();
            }
        }, "thread3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
