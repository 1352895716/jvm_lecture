package se.chapter02.thread.synchronize;

/**
 * @ClassName: Synchronize
 * @Description: TODO
 * 对于两个线程相互协作完成任务，同步锁已经不能很好地解决
 * 等待唤醒机制
 * 1.wait():让线程处于冻结状态，被wait的线程会存储到线程池中
 * 2.notify()：唤醒线程池中一个线程（任意）
 * 3.notifyAll：唤醒线程池中的所有线程
 *
 * 为什么上面几种方法都定义在Object中
 * 因为一切对象都可能作为锁
 **/
public class SynchronizeDemo03 {
    public static void main(String[] args) {
        Resource3 r3 = new Resource3();
        Thread t1 = new Thread(new Input3(r3));
        Thread t2 = new Thread(new Output3(r3));

        t1.start();
        t2.start();
    }
}

class Resource3{
    private String name;
    private String sex;
    private boolean flag = false;

    public synchronized void set(String name,String sex){
        if(flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        this.name = name;
        this.sex = sex;
        flag = true;
        notify();
    }
    public synchronized void out(){
        if(!flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(name+"................"+sex);
        flag = false;
        notify();
    }
}
class Input3 implements Runnable{
    private Resource3 r;
    public Input3(Resource3 r){
        this.r = r;
    }

    @Override
    public void run() {
        int x = 0;
        while (true){
            if(x%2==0){
                r.set("mike","男");
            }else{
                r.set("lili","女");
            }
            x++;
        }
    }
}
class Output3 implements Runnable{
    private Resource3 r;
    public Output3(Resource3 r){
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
            r.out();
        }
    }
}