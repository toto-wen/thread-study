package com.wenpd.thread;

import com.wenpd.thread.callable.MyCallAble;

import java.util.concurrent.*;

public class TestMainj {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new MyCallAble());

        Thread thread = new Thread(task, "myCallAble");
        thread.start();

        String result = task.get();
        System.out.println("执行结果：" +result);

    }

}
