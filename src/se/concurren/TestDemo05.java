package se.concurren;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName: TestDemo05
 * @Description: volatile 无锁同步，在只要求保证可见性的情况下使用volatile
 * 效率高于synchronized，但是synchronized在保证可见性的情况下，又保证了原子性
 * 在synchronized对象锁中，对象的实例变量发生改变对锁没有影响，只要引用变量不发生改变即可
 **/
public class TestDemo05 {
    //将boolean改成AtomXX发现也是可以保证原子性的
    //
    /*volatile boolean*/  AtomicBoolean running = new AtomicBoolean(true);
    public void m(){
        System.out.println("m start........");
        while (running.get()){
           // System.out.println("m run......");
        }
        System.out.println("m end........");
    }
    public static void main(String[] args) {
        TestDemo05 t = new TestDemo05();
        new Thread(t::m,"t").start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//此处休眠是为了让t线程先执行，才能看到效果，
        //否则，main线程先执行running被赋值为false，看不到任何效果
        t.running.set(false);
    }
}
