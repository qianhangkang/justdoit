package chapter_1;

public class DeadLock {
    private static final String A = "A";
    private static final String B = "B";


    public static void main(String[] args) {
        DeadLock.doDeadLock();
    }


    private static void doDeadLock() {

        Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("ThreadA:A");
                    } catch (Throwable throwable) {
                    }
                    synchronized (B) {
                        System.out.println("ThreadA:B");
                    }
                    System.out.println("释放B");//不会释放
                }
                System.out.println("释放A");
            }
        });


        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (B) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("ThreadB:B");
                    } catch (Throwable throwable) {

                    }

                    synchronized (A) {
                        System.out.println("ThreadB:A");
                    }
                    System.out.println("释放A");
                }
                System.out.println("释放B");
            }
        });


        threadA.start();
        threadB.start();

    }


}
