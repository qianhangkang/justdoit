package com.example.hk;

/**
 * synchronized
 *
 * 该关键字会让没有得到锁资源的线程进入BLOCKED状态，
 * 而后在争夺到锁资源后恢复到RUNNABLE状态，
 * 这个过程中涉及到操作系统用户模式和内核模式的转换，代价比较高
 *
 * @author HANGKANG
 * @date 2018/6/24 下午7:18
 */

public class Example2 {
    public static int count = 0;

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
                    increment();
                }
            }).start();
        }
        //让上面两个线程执行完
        Thread.sleep(2000L);
        System.out.println("count=" + count);//一定是等于20000的
    }

    /**
     * count 自增
     */
    public synchronized static void increment() {
        for (int j = 0; j < 10000; j++) {
            count++;
        }
    }
}
