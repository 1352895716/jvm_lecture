package se.concurren.test07;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: TestDemo09
 * @Description: TODO
 * 接上一个程序
 * 使用latch的await，countDown来代替notify，wait来进行通知
 * 这个就不需使用锁，当count的值为零时，线程继续运行，
 * 当不涉及同步，只涉及线程通信的时候，用synchronized+wait/nofity显得太过笨重
 * 这时应该考虑countDownlatch/cyclicbarrier/semaphore
 **/
public class TestDemo09 {
    static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        A1 a = new A1(10);
        new Thread(()->{
                if (a.size() != 5) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("已存满5个");
                System.out.println(Thread.currentThread().getName()+"已退出");

            }
        },"t2").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    System.out.println("add " + i);
                    a.add(i);
                    if(i==4){
                       latch.countDown();
                    }
                    //此处如果不休眠的话，两个线程会同时执行则打印结果可能不是这样
                    /*
                    add 1
                    add 2
                    add 3
                    add 4
                    add 5
                    已存满5个
                    t2已退出
                    add 6
                    add 7
                    add 8
                    add 9
                    空间已满
                     **/
                    try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        },"t1").start();
    }
}

