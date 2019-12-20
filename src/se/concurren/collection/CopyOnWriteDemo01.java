package se.concurren.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: CopyOnWriteDemo01
 * @Description: TODO
 *
 *
 **/
public class CopyOnWriteDemo01 {
    public static void main(String[] args) {
        List<String> list =
                //new ArrayList<>();  //线程不安全
                //new Vector<>();   //
                //写效率很低,用时是vector的60倍，但读不用枷锁，所以效率高，适合写少读多的场景
                new CopyOnWriteArrayList<>();


        Random r = new Random();
        Thread[] ths = new Thread[100];
        //创建100个线程
        for (int i=0;i<100;i++){
            ths[i] = new Thread(()->{
               for (int j=0;j<1000;j++)
                   list.add("a"+r.nextInt(10000));
            });
        }

        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(o->o.start());

        Arrays.asList(ths).forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();

        System.out.println(end-start);
        System.out.println(list.size());
    }
}
