package com.wenpd.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorsDome {

    public static void main(String[] args){

        char character = '0';
        System.out.println(character == '0');

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        for(int i = 0; i < 10; i ++){
            final  int index = i;

            executorService.schedule(new Runnable() {
                public void run() {
                    System.out.println("输入下报: "+index);
                }
            }, 3, TimeUnit.SECONDS);
        }
    }
}
