package se.concurren.threadpool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: ForkJoinPool
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/15 16:26
 * @Version 1.0
 **/
public class ForkJoinPoolDemo {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i=0;i<nums.length;i++){
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }
    static class AddTask extends RecursiveAction{
        int start,end;
        AddTask(int s,int e){
            start = s;
            end = e;
        }
        @Override
        protected void compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for(int i=start;i<end;i++)
                    sum += nums[i];
                System.out.println("from: "+ start +" to: "+ end + " = " + sum);
            }else {
                int middle = start + (end-start)/2;
                AddTask task1 = new AddTask(start,middle);
                AddTask task2 = new AddTask(middle,end);
                task1.fork();
                task2.fork();
            }
        }
    }

    static class AddTask2 extends RecursiveTask<Long>{
        int start,end;
        AddTask2(int s,int e){
            start = s;
            end = e;
        }
        @Override
        protected Long compute() {
            if(end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                return sum;
            }else{
                int middle = start + (end-start)/2;
                AddTask2 task1 = new AddTask2(start,middle);
                AddTask2 task2 = new AddTask2(middle,end);
                task1.fork();
                task2.fork();
                return task1.join()+task2.join();
            }

        }
    }
    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask2 task = new AddTask2(0,nums.length);

        fjp.execute(task);
        long  result = task.join();
        System.out.println(result);
        //System.in.read();
    }
}
