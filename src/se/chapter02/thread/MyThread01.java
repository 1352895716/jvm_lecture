package se.chapter02.thread;

/**
 * @ClassName: MyThread01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/5 8:48
 * @Version 1.0
 **/
public class MyThread01 {
    public static void main(String[] args) {
        ThreadTest1 t1 = new ThreadTest1();
        ThreadTest2 t2 = new ThreadTest2();
        ThreadTest3 t3 = new ThreadTest3();
        ThreadTest4 t4 = new ThreadTest4();
        t1.start();
        t2.start();
        //t3.start();
        //t4.start();
    }
}

class ThreadTest1 extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("hello "+i);
        }
    }
}
class ThreadTest2 extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("world "+i);
        }
    }
}
class ThreadTest3 extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("welocm "+i);
        }
    }
}
class ThreadTest4 extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("aaaaa "+i);
        }
    }
}
