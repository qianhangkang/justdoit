package chapter_two;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author qianhangkang
 * @date 2019-02-11
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 3; i > 0; i--) {
            executorService.execute(new Worker(latch, (i + 1) * 2000));
        }

        latch.await(5, TimeUnit.SECONDS);
        System.out.println(latch.getCount());
        executorService.shutdownNow();


    }


    static class Worker implements Runnable {
        private CountDownLatch latch;
        private int millis;

        public Worker(CountDownLatch latch, int millis) {
            this.latch = latch;
            this.millis = millis;
        }

        public void run() {
            try {
                Thread.sleep(millis);
                System.out.println(Thread.currentThread().getName() + " has Done:" + millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (Objects.nonNull(latch)) {
                    latch.countDown();
                }
            }
        }
    }


}
