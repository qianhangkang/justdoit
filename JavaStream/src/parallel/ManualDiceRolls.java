package parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * stream并行流模拟 蒙特卡洛模拟法
 * Author: qianhangkang
 * Date: 2018/12/7
 */
public class ManualDiceRolls {
    /**
     * 两个骰子相加的数字的概率
     *
     * 2=0.027785376990558035
     * 3=0.05555910897593917
     * 4=0.08332987096132187
     * 5=0.11110618194670165
     * 6=0.13889629193207417
     * 7=0.16667207291745423
     * 8=0.13888521693208
     * 9=0.11109345094670835
     * 10=0.08333085096132135
     * 11=0.05557005197593341
     * 12=0.027771524990565327
     *
     * 符合泊松分布的概率图。。
     *
     *          .
     *         . .
     *       ..   ..
     *     ..       ..
     *  ...           ...
     *
     */
    private static final int N = 1000000;
    private final double fraction;
    private final Map<Integer, Double> results;
    private final int numberOfThreads;
    private final ExecutorService executor;
    private final int workPerThread;

    public static void main(String[] args) {
        ManualDiceRolls roles = new ManualDiceRolls();
        roles.simulateDiceRoles();
    }

    public ManualDiceRolls() {
        fraction = 1.0 / N;//分数
        results = new ConcurrentHashMap<>();
        numberOfThreads = Runtime.getRuntime().availableProcessors();//可用核心数
        executor = Executors.newFixedThreadPool(numberOfThreads);
        workPerThread = N / numberOfThreads;
    }

    public void simulateDiceRoles() {
        List<Future<?>> futures = submitJobs();
        awaitCompletion(futures);
        printResults();
    }

    private void printResults() {
        results.entrySet()
                .forEach(System.out::println);
    }

    private List<Future<?>> submitJobs() {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(executor.submit(makeJob()));
        }
        return futures;
    }

    private Runnable makeJob() {
        return () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < workPerThread; i++) {
                int entry = twoDiceThrows(random);
                accumulateResult(entry);
            }
        };
    }

    private void accumulateResult(int entry) {
        results.compute(entry, (key, previous) ->
                previous == null ? fraction
                        : previous + fraction
        );
    }

    private int twoDiceThrows(ThreadLocalRandom random) {
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    private void awaitCompletion(List<Future<?>> futures) {
        futures.forEach((future) -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }
}

