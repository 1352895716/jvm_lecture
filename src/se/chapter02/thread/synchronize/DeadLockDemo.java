package se.chapter02.thread.synchronize;

/**
 * @ClassName: DeadLockDemo
 * @Description: 写一个简单的死锁
 * 死锁常见情景：同步锁的嵌套
 **/


public class DeadLockDemo {
    public static void main(String[] args) {
        TestDeadLock test1 = new TestDeadLock(true);
        TestDeadLock test2 = new TestDeadLock(false);

        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test2);

        t1.start();
        t2.start();
    }
}


class TestDeadLock implements Runnable{
    private boolean flag;
    public TestDeadLock( boolean flag){
        this.flag = flag;
    }
    @Override
    public void run() {
        while (true){

                if(flag){
                    synchronized (TestDeadLock.class) {
                        System.out.println("if.....TestDeadLock.class");
                        synchronized (DeadLockDemo.class) {
                            System.out.println("if.....DeadLockDemo.class");
                        }
                    }
                }else{
                    synchronized (DeadLockDemo.class) {
                        System.out.println("else.....DeadLockDemo.class");
                        synchronized (TestDeadLock.class) {
                            System.out.println("else.....TestDeadLock.class");
                        }
                    }
                }
        }

    }
}


