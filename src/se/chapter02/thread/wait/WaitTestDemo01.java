package se.chapter02.thread.wait;

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
 * 测试多生产者，多消费者产生的安全问题
 **/
//阻塞线程被唤醒后，会从阻塞处继续执行代码,但前提是它要拥有锁
//
public class WaitTestDemo01 {
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

    Resource(){
    }
    public synchronized void set() {
        while (flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        count++;
        this.name = "烤鸭" + count;
        System.out.println(Thread.currentThread().getName()+"生产了。。。。"+name);
        flag = true;
        //notify();
        notifyAll();
    }
    public synchronized void out(){
        while (!flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(Thread.currentThread().getName()+"消费了。。。。"+name);
        flag = false;
        //notify();
        notifyAll();//
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