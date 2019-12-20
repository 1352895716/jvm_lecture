package se.concurren.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName: TicketSeller01
 * @Description: TODO
 * 模拟一个简单的卖票程序
 * 多线程操作共享数据
 * 1.票会重复卖，2.数组会越界
 **/
public class TicketSeller01 {
    //此处改成Vector也不行，尽管remove是原子操作，但是判断和remove是分离的
    //解决方法就是枷锁，但是高并发情况下，每个线程都加锁效率太低
    //此时可利用ConcurrenLinkQueen来操作，
    static List<String> list = new ArrayList<>();

    static {
        for (int i=0;i<1000;i++)
            list.add("票号" + i);
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                while (list.size()>0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了----"+list.remove(0));
                }
            }).start();
        }
    }
}
