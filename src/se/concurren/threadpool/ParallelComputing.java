package se.concurren.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: ParallelComputing
 * @Description: 并行计算
 **/
public class ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer>list1 = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //System.out.println(list);
        //System.out.println(list.size());

        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Integer> list2 = new ArrayList<>();
        /*MyTask task1 = new MyTask(1,80000);
        MyTask task2 = new MyTask(1,80000);
        MyTask task3 = new MyTask(1,80000);
        MyTask task4 = new MyTask(1,80000);*/
        start = System.currentTimeMillis();
        Future<List<Integer>> f1 = service.submit(new MyTask(1,80000));
        Future<List<Integer>> f2 = service.submit(new MyTask(80001,130000));
        Future<List<Integer>> f3 = service.submit(new MyTask(130001,170000));
        Future<List<Integer>> f4 = service.submit(new MyTask(170001,200000));
        end = System.currentTimeMillis();
        list2.addAll(f1.get());
        list2.addAll(f2.get());
        list2.addAll(f3.get());
        list2.addAll(f4.get());

        System.out.println(list2);
        System.out.println(f4.isDone());
        System.out.println(end - start);


    }
    static class MyTask implements Callable<List<Integer>>{
        int startPos,endPos;
        MyTask(int s,int e){
            startPos = s;
            endPos = e;
        }
        @Override
        public List<Integer> call() throws Exception {
            return getPrime(startPos,endPos);
        }
    }
    static boolean isPrime(int num){
        for(int i =2;i<=Math.sqrt(num);i++){
            if(num%i == 0)
                return false;
        }
        return true;
    }
    static List<Integer> getPrime(int s,int e){
        List<Integer> list = new ArrayList<>();
        for(int i=s;i<=e;i++){
            if(isPrime(i))
                list.add(i);
        }
        return list;
    }
}
