package se.concurren.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: MapTest01
 * @Description: TODO
 * Collections.synchronizedXXX()方法，将一个非线程安全的集合变成线程安全的
 *
 **/
//
    // Hashtable 和加了锁的HashMap效率差不多，一般并发量可以使用

    // ConcurrentHashMap    如果高并发不需要排序，就用这个
    // ConcurrenHashMap     使用的分段式锁，所以效率高一点
    // ConcurrentSkipListMap 如果高并发且需要排序
    //
public class MapTest01 {

    public static void main(String[] args) {
       /* Map<String,String> map = new HashMap<>();
        map = Collections.synchronizedMap(map);*/  //744 579 568 591 720
        //Map<String,String> map = new Hashtable<>();//565 608 573 557 594 582 601

        //Map<String,String> map = new ConcurrentHashMap<>();  //653 593 434 437 430 495 612
        Map<String,String> map = new ConcurrentSkipListMap<>(); //788 686 823 699 737
        Random r = new Random();
        Thread[] ts = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ts.length);
        long start = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            Map<String, String> finalMap = map;
            ts[i] = new Thread(()->{
                for (int j=0;j<10000;j++)
                    finalMap.put("a"+r.nextInt(10000),"a"+r.nextInt(10000));
                latch.countDown();
            });
        }
        Arrays.asList(ts).forEach(t->t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(end-start);


    }
}
