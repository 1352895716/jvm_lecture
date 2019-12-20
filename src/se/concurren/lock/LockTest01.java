package se.concurren.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest01
 * @Description: TODO
 * ReentrantLock可以指定为公平锁new ReentrantLock(true);
 * 当然公平锁效率较低，
 * synchronized默认是非公平锁
 **/
public class LockTest01 implements Runnable{

    private Lock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        LockTest01 t = new LockTest01();
        new Thread(t).start();
        new Thread(t).start();
    }

    @Override
    public void run() {
        for (int i=0;i<50;i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }
}
