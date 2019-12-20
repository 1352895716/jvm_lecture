package se.concurren.threadpool;

import java.util.concurrent.*;

/**
 * @ClassName: FutureDemo01
 * @Description: TODO
 **/
public class FutureDemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<Integer>(()->{
            Thread.sleep(1000);
            return 1000;
        });

        new Thread(task).start();
        System.out.println(task.get());
        //***************************************
        ExecutorService service = Executors.newFixedThreadPool(6);
        Future<Integer> f = service.submit(()->{
           Thread.sleep(100);
            return 1;
        });

        System.out.println(f.isDone());//是否计算完成得到结果
        System.out.println(f.get());//get()没有得到结果前会一直阻塞
        System.out.println(f.isDone());

    }
}
