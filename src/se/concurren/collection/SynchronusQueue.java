package se.concurren.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName: SynchronusQueue
 * @Description: TODO
 * SynchronousQueue
 **/
public class SynchronusQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String>strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.put("aaa");//底层使用的LinkedTransferQueue的transfer,所以在调用该方法前，必须有空闲的消费者线程
        //strs.add("aaa");//使用这个方法会报错，因为该队列容量为0，放进去的消息必须立马被消费
        System.out.println(strs.size());

    }
}
