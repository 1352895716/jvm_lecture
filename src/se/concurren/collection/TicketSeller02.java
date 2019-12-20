package se.concurren.collection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName: TicketSeller02
 * @Description: TODO
 * 解决同步问题与效率问题的卖票程序
 **/
public class TicketSeller02 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i=0;i<1000;i++)
        tickets.add("票号" + i);
    }
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
               while (true){
                   //实现同步但么有利用锁的原理，所以效率很高
                   String str = tickets.poll();
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   if(str == null)break;
                   System.out.println("销售了----"+str);
               }
            }).start();
        }
    }
}
