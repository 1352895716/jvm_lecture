package se.chapter02.thread;

/**
 * @ClassName: MyThread02
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/5 9:56
 * @Version 1.0
 **/
public class MyThread02 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread1());
        t1.start();
    }
}
class Thread1 implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("hello "+i);
        }
    }
}