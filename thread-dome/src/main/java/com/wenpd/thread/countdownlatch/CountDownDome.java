package com.wenpd.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownDome {

    /**
     * 需设置需要先执行完的线程数为初始值。
     */
    private CountDownLatch countDownLatch = new CountDownLatch(3);

    /**
     * 运动员方法，运动员线程调用
     */
    public void racer(Long time){
        String name = Thread.currentThread().getName();
        System.out.println("当前正在准备运动员为："+name);
        // 睡眠2秒
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 运动员休眠完毕
        System.out.println("当前运动员 "+name+" 完成休眠");
        // 计数器减一
        countDownLatch.countDown();
    }


    /**
     * 教练方法，由教练线程调用
     */
    public void coach(){
        String name = Thread.currentThread().getName();
        System.out.println("教练"+name+"正在等待所有运动员准备完毕");
        // 调用CountDownLatch的await方法等待其他线程执行完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //
        System.out.println("运动员集合完毕，教练"+name+"开始训练");
    }


    public static void main(String[] args){
        final CountDownDome countDownDome = new CountDownDome();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                countDownDome.racer(2000l);
            }
        }, "运动员1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                countDownDome.racer(3000l);
            }
        }, "运动员2");

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                countDownDome.racer(5000l);
            }
        }, "运动员3");

        Thread threadTeacher = new Thread(new Runnable() {
            public void run() {
                countDownDome.coach();
            }
        }, "教练");

        threadTeacher.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
