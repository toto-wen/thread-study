package com.wenpd.thread.callable;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyCallAble implements Callable<String> {
    public String call() throws Exception {

        for(int i = 0; i < 10; i ++){
            System.out.println(Thread.currentThread().getName()+" 执行时间 "+new Date().getTime()+" 循环次数 "+i);
        }

        return "call线程执行完成";
    }
}
