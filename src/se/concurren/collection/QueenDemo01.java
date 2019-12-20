package se.concurren.collection;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: QueenDemo01
 * @Description: TODO
 * 在Link中，因为是无界队列除非内存溢出，offer才会停止
 *
 * put()和take()方法实现了同步加阻塞，   其他方法只是实现了简单的同步，阻塞还需自己处理
 **/
public class QueenDemo01 {
    static BlockingQueue<String> bq = new LinkedBlockingQueue<>();
    Random r = new Random();
    public static void main(String[] args) {
        new Thread(()->{
            for (int i=0;i<100;i++)
                try {
                    bq.put("a"+i);//
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"produce").start();

        for (int i=0;i<5;i++){
            new Thread(()->{
                while (true){
                    try {
                        System.out.println(Thread.currentThread().getName()+" take "+bq.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"consumer0"+i).start();
        }
    }
}
