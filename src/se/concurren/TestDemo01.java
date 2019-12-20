package se.concurren;

/**
 * @ClassName: TestDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/11 12:04
 * @Version 1.0
 **/
public class TestDemo01 {
    public static void main(String[] args) {
        T t = new T();
        for (int i=0;i<10;i++){
            new Thread(t,"THREAD"+i).start();
        }
    }
}
class T implements Runnable{
    private  int count = 10;
    @Override
    public void run() {
        count--;
        /*try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(Thread.currentThread().getName()+"  count = "+count);
    }
}
