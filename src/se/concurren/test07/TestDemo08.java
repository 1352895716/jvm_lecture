package se.concurren.test07;

/**
 * @ClassName: TestDemo08
 * @Description: TODO
 * 错误示范：
 * t2线程先开始执行，到a.wait() 阻塞，释放锁，然后t1开始执行
 * t1 运行到i==4，唤醒了t2，但是notify不释放锁，接着下面的sleep也不释放锁
 * 直接运行结束后才释放锁，此时t2猜得到锁，继续从阻塞处执行代码到结束
 * 下面是错误的运行结果
add 0
add 1
add 2
add 3
add 4
add 5
add 6
add 7
add 8
add 9
空间已满
已存满5个
t2已退出

 所以在t2唤醒t1后，立马把自己阻塞，待t1执行完后再唤醒t2，即可
 但这样虽然相比于while循环提高了效率，但是使用wait,notify还得先试用锁，
 还是比较麻烦，且效率是否能再次提高，TestDemo09来揭晓
 **/
public class TestDemo08 {
    public static void main(String[] args) {
        A1 a = new A1(10);
        new Thread(()->{
            synchronized (a){
                if (a.size() != 5) {
                    try {
                        a.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                a.notify();
                System.out.println("已存满5个");
                System.out.println(Thread.currentThread().getName()+"已退出");

            }
        },"t2").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (a) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("add " + i);
                    a.add(i);

                    if(i==4){
                        a.notify();
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                      try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },"t1").start();




    }
}
class A1{
    int len;
    int ptr = 0;
    Object[] arr = null;

    A1(int len){
        this.len = len;
        arr = new Object[this.len];
    }

    public  void add(Object num){
        arr[ptr++] = num;
        if (ptr==len)
            System.out.println("空间已满");
    }
    public  int size(){
        return this.ptr;
    }
}