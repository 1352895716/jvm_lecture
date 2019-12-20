package se.chapter02.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: MyThread03
 * @Description: TODO
 * 线程的第三种创建方法
 *
 * interrupt  清除线程的中断状态
 * join       代替当前调用它的线程
 * setDaemon  将该线程标记为守护线程或用户线程。
 **/
public class MyThread03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //
        MyCallable mc = new MyCallable();
        //可以获取执行完后的结果，也可以作为参数专递给Thread对象
        FutureTask<String> ft = new FutureTask(mc);
        //
        new Thread(ft).start();
        System.out.println("   "+ft.get());
    }

}
class MyCallable implements Callable<String>{

    private boolean flag = true;
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    @Override
    public String call() throws Exception {
        int x = 0;
        while (flag){
            Thread.sleep(100);
            System.out.println("循环了"+x+++"次");
            if(x==100)
                setFlag(false);
        }
        return x+"";
    }
}