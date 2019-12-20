package se.chapter02.thread.wait2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: WaitTestDemo01
 * @Description: TODO
 * 1.对于线程协作任务，简单的同步已经无法解决问题，还需要唤醒机制
 * 2.当然这也解决单线程的协作，对于多线程的协作问题还是会产生线程安全问题
 * 3.例如，连续生产两次（或多次）致使前面的产品没有被消费（生产者唤醒了阻塞中的生产者）
 *         对于同一件产品连续消费两次（或多次），（消费者唤醒了阻塞中的消费者）
 *         (根本原因，阻塞线程被唤醒且抢到执行权后，会从阻塞处继续执行代码)
 *          当然可以用while来代替if判断来解决问题，但这样也会导致死锁
 *          所以就使用nofity代替nofityAll()来确保一定会唤醒对方线程
 *          当然这样做的代价是比较大的，jdk1.5之前也确实都是这么做的，到了jdk1.5之后出现了一些新的做法
 *
 *          下面演示jdk5.0之后lock的改进做法
 *
 *          Lock接口：出现替代了同步代码块或者同步函数，将同步的隐式锁操作变成显式锁操作
 *          同时更加灵活，可以一个锁上家多组监视器
 *          lock：获取锁
 *          unlock：释放锁，通常定义在finally代码块中
 *
 *          Condition接口：出现代替了Object中的wait,nofity,nofityAll方法
 *          将这些监视器方法单独进行了封装，变成Condition监视器对象，可以任意锁进行组合
 * 测试多生产者，多消费者产生的安全问题
 **/
//阻塞线程被唤醒后，会从阻塞处继续执行代码
//
public class WaitTestDemo02 {
    public static void main(String[] args) {
        Resource r = new Resource();
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

/*
 * @Autor: 13528
 * @Description: TODO
 * 封装一个资源类，用来被消费者生产者所使用
 **/
class Resource{
    private String name;
    private int count=0;
    private boolean flag=false;
    //----------------------------------------------------
    //显示的枷锁和解锁，并且有针对性的唤醒
    //创建一个锁对象
    Lock lock = new ReentrantLock();
    //通过已有的锁获得该锁上的监视器对象，两组，一组监视生产者，一组监视消费者
    Condition produce_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();
    //--------------------------------------------------------

    Resource(){
    }
    public void set() {
        lock.lock();
        try {
            while (flag)
                    produce_con.await();
            count++;
            this.name = "烤鸭" + count;
            System.out.println(Thread.currentThread().getName()+"5.0生产了。。。。"+name);
            flag = true;
            //notify();
            //notifyAll();
            consumer_con.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public  void out(){
        lock.lock();
       try {
           while (!flag)
                 consumer_con.await();

           System.out.println(Thread.currentThread().getName()+"5.0消费了。。。。"+name);
           flag = false;
           //notify();
           //notifyAll();//
           produce_con.signal();
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
    }
}

class Produce implements Runnable{
    private Resource r;
    public Produce(Resource r){
        this.r = r;
    }
    @Override
    public void run() {
        while (true){
            r.set();
        }

    }
}

class Consumer implements Runnable{
    private Resource r;
    public Consumer(Resource r){
        this.r = r;
    }
    @Override
    public void run() {
        while (true){
            r.out();
        }

    }
}