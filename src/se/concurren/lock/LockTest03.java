package se.concurren.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest03
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/12 21:52
 * @Version 1.0
 **/
public class LockTest03 {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(()->{

            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"start.....");
                Thread.sleep(Integer.MAX_VALUE);
                System.out.println(Thread.currentThread().getName()+"end.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        Thread t2 = new Thread(()->{
            boolean locked = false;
            try {
                lock.lockInterruptibly();
                locked = true;
                System.out.println(Thread.currentThread().getName()+"start.....");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"end.....");
            } catch (InterruptedException e) {
                //e.printStackTrace();\
                System.out.println(Thread.currentThread().getName()+"被打断了！");
            }finally {
                if(locked)
                    lock.unlock();
            }
        });
        t2.start();

        t2.interrupt();

    }
}
