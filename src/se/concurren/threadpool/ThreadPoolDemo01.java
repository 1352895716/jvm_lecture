package se.concurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ThreadPoolDemo01
 * @Description: TODO
 * Executor
 * ExecutorService
 * Callable = Runable
 * Executors
 **/
public class ThreadPoolDemo01 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i=0;i<6;i++){
            service.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        service.shutdown();//关闭线程池（等所有任务执行完，才关闭）
        System.out.println(service.isTerminated());//任务是否都一执行完毕
        System.out.println(service.isShutdown());//
        System.out.println(service);

        Thread.sleep(3000);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

    }
}
