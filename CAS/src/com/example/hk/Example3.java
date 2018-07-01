package com.example.hk;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic 底层采用了CAS(Compare And Swap)机制
 * 该机制使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B
 * 更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B
 *
 * 从思想上来说，synchronized属于悲观锁，CAS属于乐观锁
 *
 * CAS缺点：
 * 1. CPU开销较大
 * 在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力
 *
 * 2. 不能保证代码块的原子性
 * CAS机制所保证的只是一个变量的原子性
 *
 * 3. ABA的问题
 * 一个线程one从内存位置V中取出A，这时候另一个线程two也从内存中取出A，并且two进行了一些操作变成了B，
 * 然后two又将V位置的数据变成A，这时候线程one进行CAS操作发现内存中仍然是A，然后one操作成功。
 * 尽管线程one的CAS操作成功，但是不代表这个过程就是没有问题的。
 *
 *
 *
 * @author HANGKANG
 * @date 2018/6/24 下午7:15
 */

public class Example3 {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        //开启两个线程，每个线程对count自增10000次
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                        System.out.println("Thread begin run");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 10000; j++) {
                        count.incrementAndGet();
                    }
                }
            }).start();
        }
        //等待2s，让上面两个线程执行完
        Thread.sleep(2000L);
        System.out.println("count=" + count);//一定是等于20000的
    }

}
