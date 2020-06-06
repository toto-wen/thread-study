package com.wenpd.thread.domo;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountNum {

    private Integer num = 1;

    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();


    public void one(){
        while (true) {
//            synchronized (this) {
//                System.out.println("进入线程："+1);
//                if (num.intValue() == 1) {
//                    System.out.println("打印: " + num);
//                    num = 2;
//                    this.notifyAll();
//                } else {
//                    try {
//                        this.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            lock.lock();
            try{
                System.out.println("进入线程："+1);
                if (num.intValue() == 1) {
                    System.out.println("打印: " + num);
                    num = 2;
                    condition.signalAll();
                } else {

                        condition.await();

                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

    public void two(){
        while (true) {
//            synchronized (this) {
//                System.out.println("进入线程："+2);
//                if (num.intValue() == 2) {
//                    System.out.println("打印: " + num);
//                    num = 3;
//                    this.notifyAll();
//                } else {
//                    try {
//                        this.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            lock.lock();
            try {
                System.out.println("进入线程："+2);
                if (num.intValue() == 2) {
                    System.out.println("打印: " + num);
                    num = 3;
                    condition.signalAll();
                } else {
                        condition.await();

                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public void three(){
        while (true) {
//            synchronized (this) {
//                System.out.println("进入线程："+3);
//                if (num.intValue() == 3) {
//                    System.out.println("打印: " + num);
//                    num = 1;
//                    this.notifyAll();
//                } else {
//                    try {
//                        this.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            lock.lock();
            try {
                System.out.println("进入线程："+3);
                if (num.intValue() == 3) {
                    System.out.println("打印: " + num);
                    num = 1;
                    condition.signalAll();
                } else {
                        condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
