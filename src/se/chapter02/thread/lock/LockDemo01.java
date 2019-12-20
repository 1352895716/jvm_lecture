package se.chapter02.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/10 16:23
 * @Version 1.0
 **/
public class LockDemo01 {
    public static void main(String[] args) {
        BoundedBuffer r = new BoundedBuffer();

        Thread t0 = new Thread(new Produce(r));
        Thread t1 = new Thread(new Produce(r));
        Thread t2 = new Thread(new Consumer(r));
        Thread t3 = new Thread(new Consumer(r));

        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition produce_con  = lock.newCondition();
    final Condition consumer_con = lock.newCondition();

    final Object[] items = new Object[1];
    int putptr, takeptr, count;

    public void put(Object x) {
        lock.lock(); try {
            while (count == items.length)
                produce_con.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            consumer_con.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { lock.unlock(); }
    }

    public Object take()  {
        lock.lock(); try {
            while (count == 0)
                consumer_con.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            produce_con.signal();
            return x;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { lock.unlock(); }
        return null;
    }
}

class Produce implements Runnable{
    private BoundedBuffer bb;
    Produce(BoundedBuffer bb){
        this.bb = bb;
    }
    @Override
    public void run() {
        int x = 0;
        while (true){
            bb.put(x);
            System.out.println(Thread.currentThread().getName()+"生产了  "+x);
            x++;
        }
    }
}
class Consumer implements Runnable{
    private BoundedBuffer bb;
    Consumer(BoundedBuffer bb){
        this.bb = bb;
    }
    @Override
    public void run() {
        while (true){

            System.out.println(Thread.currentThread().getName()+"消费了  "+bb.take());
        }
    }
}

