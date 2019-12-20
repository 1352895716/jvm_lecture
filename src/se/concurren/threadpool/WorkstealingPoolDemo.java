package se.concurren.threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: WorkstealingPoolDemo
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/15 13:00
 * @Version 1.0
 * WorkStealingPool底层是ForkJoinPool实现的
 * 这个线程池里面的线程都是守护线程、
 * 应用于任务分配不是很均匀
 **/
public class WorkstealingPoolDemo {
    public static void main(String[] args) throws IOException {
        //根据cpu的核数，
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000)); //daemon
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        //由于产生的是精灵线程（守护线程，后台线程），主线程不阻塞的话看不到输出
        System.in.read();
    }
    static class R implements Runnable{

        int time;
        R(int t){
            time = t;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+" "+Thread.currentThread().getName());
        }
    }

}
