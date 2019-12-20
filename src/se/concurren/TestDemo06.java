package se.concurren;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestDemo06
 * @Description: TODO
 *
 * 错误理解：没有手动进行线程调度时，线程会一直执行，知道结束，释放锁
 * 正确理解：
 * 枷锁之后无论几核处理器，都会只有一个进程执行，
 **/
public class TestDemo06 {
    private /*volatile*/ int count = 0;
    public  void m(){
        for (int i =0;i<100000;i++) {
            count++;

            System.out.println(Thread.currentThread().getName());
        }
    }
    @Test
    public void test(){
        TestDemo06 t = new TestDemo06();
        List<Thread> threads = new ArrayList<>();

        for (int i=0;i<2;i++)
            threads.add(new Thread(t::m));

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();//意为所有线程执行完毕，再去执行main线程,否则main线程可能先抢到cpu，直接输出0结束
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
    public static void main(String[] args) {
        TestDemo06 t = new TestDemo06();
        for (int i=0;i<5;i++) {
            new Thread(t::m).start();

        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //经常输出0，是因为main线程先抢到了cpu，直接就输出了
        System.out.println(t.count);
    }
}
