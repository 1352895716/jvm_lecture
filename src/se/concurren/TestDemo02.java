package se.concurren;

/**
 * @ClassName: TestDemo02
 * @Description: 同步方法和非同步方法是否可以同时调用
 **/
public class TestDemo02 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" start.....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" end.....");
    }
    public void m2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2.....");
    }

    public static void main(String[] args) {
        TestDemo02 t = new TestDemo02();
        new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();
    }
}
