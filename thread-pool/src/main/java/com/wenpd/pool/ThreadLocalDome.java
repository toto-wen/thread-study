package com.wenpd.pool;

public class ThreadLocalDome {
    // 创建银行对象：钱，存款，取款
    static class Bank{
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            protected Integer initialValue(){
                return 0;
            }
        };

        public Integer get(){
            return threadLocal.get();
        }

        public void set(Integer money){
            Integer moneyOld = threadLocal.get();
            threadLocal.set(money+moneyOld);
        }
    }

    // 创建一个转账对象：从银行中取钱，存钱，转账，保存到账户
    static class Transfer implements Runnable{
        private Bank bank;

        public Transfer(Bank bank) {
            this.bank = bank;
        }

        public void run() {
            for(int i = 0; i < 10; i ++){
                bank.set(10);
                System.out.println(Thread.currentThread().getName()+"账户余额："+bank.get());
            }
        }
    }

    // 在main方法中使用这两个对象模拟转账，学习使用ThreadLocal
    public static void main(String[] args){
        Bank bank = new Bank();
        Transfer transfer = new Transfer(bank);

        Thread thread1 = new Thread(transfer,"用户1");
        Thread thread2 = new Thread(transfer,"用户2");
        Thread thread3 = new Thread(transfer,"用户3");

        thread1.start();
        thread2.start();
        thread3.start();


    }

}
