package com.wenpd.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁示例
 */
public class ReadWriteLockMode {

    private Map<String, String> map = new HashMap<String, String>(); // 操作对象
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public String get(String key){
        // 读方法
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读操作开始执行");
            Thread.sleep(3000);
            return map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"已解锁读操作，读操作结束");
        }
    }

    public void  put(String key, String value){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入数据开始");
            Thread.sleep(3000);
            map.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"写锁释放完毕");
        }
    }

    public static void main(String[] args){
        final ReadWriteLockMode readWriteLockMode = new ReadWriteLockMode();

        readWriteLockMode.put("key1", "value1");
        new Thread("wenpd-thread-1"){
            public void run(){
                System.out.println(readWriteLockMode.get("key1"));
            }
        }.start();

        new Thread("wenpd-thread-2"){
            public void run(){
                System.out.println(readWriteLockMode.get("key1"));
            }
        }.start();
        // 写线程
        new Thread("write-thread-1"){
            public void run(){
                readWriteLockMode.put("key1", "value2");
            }
        }.start();

        new Thread("wenpd-thread-3"){
            public void run(){
                System.out.println(readWriteLockMode.get("key1"));
            }
        }.start();

    }

}
