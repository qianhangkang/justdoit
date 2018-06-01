package rigister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类似spring的单例实现
 * 单例注册表
 *
 * @author HANGKANG
 * @date 2018/6/1 下午5:10
 */

public class SingletonRigister {
    private static Map rigister = new ConcurrentHashMap();//考虑多线程

    protected SingletonRigister() {
        System.out.println("initialize SingletonRigister");
    }

    public SingletonRigister getInstanceByName(String name) {
        //省略对于传入的name进行处理
        //name = transformName(name);

        Object bean = null;
        if (rigister.get(name) == null) {
            try {
                synchronized (SingletonRigister.class) {
                    rigister.put(name, Class.forName(name).newInstance());
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return (SingletonRigister) rigister.get(name);
    }

}
