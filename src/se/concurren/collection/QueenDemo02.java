package se.concurren.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: QueenDemo02
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/14 11:40
 * @Version 1.0
 **/
public class QueenDemo02 {
    public static void main(String[] args) throws InterruptedException {
        //有界队列，
        BlockingQueue<String> bq = new ArrayBlockingQueue(10);

        bq.put("AAAA");//如果已满，会阻塞
        bq.add("AAAA");  //队列如果已满，会报异常
        bq.offer("AAAA");//队列如果已满,会返回false
        //尝试添加，如果一秒后还没添加成功！就放弃
        bq.offer("AAA",1, TimeUnit.SECONDS);
    }
}
