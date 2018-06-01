package lazy;

/**
 * 懒汉式
 *
 * @author HANGKANG
 * @date 2018/6/1 下午2:30
 */

public class SingletonLazy {
    private static SingletonLazy instance = null;

    private SingletonLazy() {
        System.out.println("initialize SingletonLazy");//可能很耗时
    }

    /**
     * 为了防止多线程下出现破坏单利的情况，需要对本方法进行同步
     * @return SingletonLazy单利
     */
    public synchronized static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }


}
