package se.concurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: SingleThreadPool
 * @Description: TODO
 * newSingleThreadExecutor：线程池中就一个线程，主要是为了保证任务的有序性
 *
 **/
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for(int i =0;i<5;i++){
            int j = i;
            service.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }

        service.shutdown();
    }
}
