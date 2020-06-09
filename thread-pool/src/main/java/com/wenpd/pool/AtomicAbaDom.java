package com.wenpd.pool;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决CAS的ABA问题，使用版本号，AtomicStampedReference
 */
public class AtomicAbaDom {
    static AtomicStampedReference<Integer> n;

    public static void main(String[] args) throws InterruptedException {
        n = new AtomicStampedReference<Integer>(0,0);
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 1000; i ++){
                    Integer reference;
                    int stamp;
                    do{
                        stamp = n.getStamp();
                        reference = n.getReference();
                    }while (!n.compareAndSet(reference, reference+1, stamp, stamp+1));
//                    atomicN.addAndGet(1);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 1000; i ++){
                    Integer reference;
                    int stamp;
                    do{
                        stamp = n.getStamp();
                        reference = n.getReference();
                    }while (!n.compareAndSet(reference, reference+1, stamp, stamp+1));
//                    atomicN.addAndGet(1);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("n的最终值："+n);
        System.out.println("atomicN的最终值："+n.getReference());
    }
}
