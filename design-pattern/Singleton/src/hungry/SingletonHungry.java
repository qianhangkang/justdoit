package hungry;

/**
 * 恶汉式
 * @author HANGKANG
 * @date 2018/6/1 下午2:23
 */

public class SingletonHungry {
    private static SingletonHungry instance = new SingletonHungry();


    private SingletonHungry() {
        System.out.println("initialize SingletonHungry");
    }

    public static SingletonHungry getInstance() {
        return instance;
    }


    public static void say(String s) {
        System.out.println(s);
    }




    public static void main(String[] args) {
        SingletonHungry.say("Hi~~~");
        SingletonHungry.say("Hi again~~~");
        /*
         *  initialize SingletonHungry
         *  Hi~~~
         *  Hi again~~~
         */

        /*
         *  虽然没有使用单例类，但是单例类的初始化函数还是调用了，而有时并不需要其初始化
         */
    }

}
