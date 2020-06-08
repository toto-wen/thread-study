package com.wenpd.thread.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreMode {

    static class Work implements Runnable{
        // 工人号
        private Integer workerNum;
        // 机器数
        private Semaphore semaphore;

        public Work(Integer workerNum, Semaphore semaphore){
            this.semaphore=semaphore;
            this.workerNum=workerNum;
        }

        public void run() {
            // 获取机器
            try {
                semaphore.acquire();

                // 打印工人获取到机器，工人开始工作
                String name = Thread.currentThread().getName();
                System.out.println(name+"工人拿到机器开始工作....");
                // 线程睡眠2秒，模拟工人使用机器工作
                Thread.sleep(2000l);
                // 使用完毕，释放机器资源
                semaphore.release();
                System.out.println(name+"工人使用完毕,释放机器");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        int workers = 8;// 工人数
        Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < workers; i ++){
            new Thread(new Work(i, semaphore), "thread"+i).start();
        }
    }
}
