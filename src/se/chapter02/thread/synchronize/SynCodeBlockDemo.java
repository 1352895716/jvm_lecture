package se.chapter02.thread.synchronize;

/**
 * @ClassName: SynCodeBlockDemo
 * @Description: 卖票
 *
 * 线程安全问题的产生
 * 1.多线程在操作共享的数据
 * 2.操作共享数据的代码有多条
 * 当一个线程在操作共享数据的多条代码块中，其他线程也参与了运算，就会导致线程安全问题的产生。
 *
 * 此时可以使用同步代码块来解决
 *
 * 同步的好处：解决了线程安全问题，
 *       弊端：相对降低了效率，因为同步的线程都会判断同步锁
 * 使用同步的前提：同步中必须有多个线程，且使用同一把锁
 *
 **/


public class SynCodeBlockDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread t1 = new Thread(ticket);
        Thread t2 = new Thread(ticket);
        Thread t3 = new Thread(ticket);

        t1.start();
        t2.start();
        t3.start();
        System.out.println("----------------");
    }
}
class Ticket implements Runnable{
    private int num = 100;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true){
            show();
        }
    }
    public  void show(){
        if (num > 0) {
            try {
                //验证sleep释放执行权，不释放锁；我不干，你们也别想干
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(2000); //休眠时为了能出现负数，使多线程出现问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " num:" + num--);

        }
    }

   /* @Override
    public void run() {
        while (true) {
            synchronized (obj){
                if (num > 0) {
                    try {
                        Thread.sleep(100); //休眠时为了能出现负数，使多线程出现问题
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " num:" + num--);

                }
            }
        }
    }*/
}
