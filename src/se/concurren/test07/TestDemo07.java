package se.concurren.test07;

/**
 * @ClassName: TestDemo07
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/12 9:59
 * @Version 1.0
 **/
public class TestDemo07 {
    public static void main(String[] args) {
        A a = new A(10);
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("add "+i);
                a.add(i);
            }
        }).start();
        new Thread(()->{
            while (true) {
                if (a.size() == 5) {
                    System.out.println("已存满5个");
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName()+"已退出");
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class A{
    int len;
    volatile int ptr = 0;
    Object[] arr = null;

    A(int len){
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
