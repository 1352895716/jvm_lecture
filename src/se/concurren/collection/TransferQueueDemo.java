package se.concurren.collection;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName: TransferQueueDemo
 * @Description: TODO
 *  LinkedTransferQueue是一个特殊的无界阻塞队列，除了拥有和无界队列一样的方法外，还有一个特殊的方法transfer("aaa" + i)
 *  transfer()不会与队列进行交互，直接交给消费者，若无空闲的消费者线程则会阻塞
 *  (注意：在使用transfer之前，必须有空闲的消费者线程，否则程序会完全阻塞)
 **/
public class TransferQueueDemo {
    public static void main(String[] args) throws InterruptedException {


        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            while (true)
                try {
                    System.out.println(strs.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }).start();

        for (int i=0;i<100;i++) {
            //strs.offer("aaa" + i);//向队列添加元素
            //strs.put("aaa" + i);  //相对列添加元素，由于是无界所以不会阻塞
            //System.out.println(strs.size());
            strs.transfer("aaa" + i); //不会与队列进行交互，直接交给消费者，
            System.out.println(strs.size());
        }

    }
}
