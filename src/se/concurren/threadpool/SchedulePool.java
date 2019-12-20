package se.concurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SchedulePool
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/15 10:57
 *
 **/
public class SchedulePool {
    public static void main(String[] args) throws InterruptedException {
        //定时任务，和delayQueue相似
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName());
        },0,500, TimeUnit.MILLISECONDS);

        System.out.println(service);
        Thread.sleep(20000);
        System.out.println(service);
        service.shutdown();
    }
}
