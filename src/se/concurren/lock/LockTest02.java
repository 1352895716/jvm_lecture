package se.concurren.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest02
 * @Description: TODO
 * lock的trylock()尝试锁定，不管锁定与否，方法都将继续执行！
 * 可以根据tryLock的返回值判断是否锁定
 * tryLock()可以指定时间，到时自动放弃获取锁
 **/
public class LockTest02 {
    private Lock lock= new ReentrantLock(true);
    void m1(){
        for (int i=0;i<10;i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    void m2(){
        boolean locked = false;
        try {
            //时间到后，不管有没有获得锁，方法都将会继续执行
            //此时可根据返回值判断是否获得锁
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+locked);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            if(locked)
                lock.unlock();
            else
                System.out.println(Thread.currentThread().getName()+"没有获得锁！");
        }
    }
    public static void main(String[] args) {
        LockTest02 t = new LockTest02();
        new Thread(t::m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}
