package chapter_3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程Debug测试
 * @author qianhangkang
 * @date 2019-02-21
 */
public class MultiThreadDebugTest {
    private static Executor executor = Executors.newFixedThreadPool(10);
    private static AtomicInteger atomicInteger = new AtomicInteger(10);

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {
            executor.execute(()->{
                int andIncrement = atomicInteger.getAndIncrement();
                System.out.println(andIncrement);
            });
        }




    }



}
