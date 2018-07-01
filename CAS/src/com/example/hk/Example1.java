package com.example.hk;

/**
 * @author HANGKANG
 * @date 2018/6/24 下午7:08
 */

public class Example1 {
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
                    for (int j = 0; j < 10000; j++) {
                        count++;
                    }
                }
            }).start();
        }
        //让上面两个线程执行完
        Thread.sleep(2000L);
        System.out.println("count=" + count);//一定是小于20000的
    }
}
