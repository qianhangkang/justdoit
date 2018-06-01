package lazy;

/**
 * @author HANGKANG
 * @date 2018/6/1 下午4:41
 */

public class SingletonLazy2 {
    private static volatile SingletonLazy2 instance = null;

    private SingletonLazy2() {
        System.out.println("initialize SingletonLazy2");//可能很耗时

    }

    /**
     * 双重锁检查，instance需要被声明为volatile
     * @return
     */
    public static SingletonLazy2 getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy2.class) {
                if (instance == null) {
                    instance = new SingletonLazy2();
                }
            }
        }
        return instance;
    }
}
