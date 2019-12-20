package se.concurren.threadlocal;

/**
 * @ClassName: ThreadLocalDemo01
 * @Description: TODO
 * volatile 可以保证可见性，
 * 写了不出问题，不写也不一定出问题
 **/
public class ThreadLocalDemo01 {
    volatile private static Person p = new Person();
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(1010);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();
    }
}
class Person{
    String name = "zhangsan";
}
