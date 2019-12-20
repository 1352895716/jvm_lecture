package se.concurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: CacheThreadPoolDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/14 22:25
 * @Version 1.0
 **/
public class CacheThreadPoolDemo01 {
    public static void main(String[] args) throws InterruptedException {
        //池中空闲线程池的最长存活时间默认是60秒，可以自己指定
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for(int i=0;i<2;i++){
            service.execute(()->{
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        Thread.sleep(80000);
        System.out.println(service);
        service.shutdown();
    }

}
