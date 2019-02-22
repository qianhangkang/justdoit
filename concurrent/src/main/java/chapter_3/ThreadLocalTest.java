package chapter_3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author qianhangkang
 * @date 2019-02-21
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static Executor executor = Executors.newFixedThreadPool(8);


    public static void main(String...args) {
        threadLocal.set(-1);
        for (int i=0; i<5; i++) {
            final int ii = i;
            executor.execute(()->{
                threadLocal.set(ii);
                System.out.println(threadLocal.get());
            });
        }
        System.out.println(threadLocal.get());
    }





    /**
     * ThreadLocal只是一个帮助你拿到存储在线程中的value的类
     * ThreadLocal.get的时候会拿到当前线程的ThreadLocal.ThreadLocalMap threadLocals，
     * 实际上数据就是存储在线程中的一个Map，再根据ThreadLocal这个类作为key去拿
     */
}
