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
public class SynchronizeDemo02 {
    public static void main(String[] args) {
        Resource2 r2 = new Resource2();
        Thread t1 = new Thread(new Input2(r2));
        Thread t2 = new Thread(new Output2(r2));

        t1.start();
        t2.start();
    }
}

class Resource2{
    public String name;
    public String sex;
    public boolean flag = false;
}

class Input2 implements Runnable{
    private Resource2 r;
    public Input2(Resource2 r){
        this.r = r;
    }
    @Override
    public void run() {
        int x = 1;
        while (true){
            synchronized (r){
                if (r.flag)
                    try {
                        r.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                if(x%2 == 1){
                    r.name = "mike";
                    r.sex = "男";
                }else{
                    r.name = "lili";
                    r.sex = "女";
                }
                r.flag = true;
                r.notify();
            }
            x++;
        }
    }
}

class Output2 implements Runnable{

    private Resource2 r;
    public Output2(Resource2 r){
        this.r = r;
    }
    @Override
    public void run() {
        while (true){
            synchronized (r) {
                if(!r.flag)
                    try {
                        r.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println(r.name + "..............." + r.sex);
                r.flag = false;
                r.notify();
            }
        }
    }
}
