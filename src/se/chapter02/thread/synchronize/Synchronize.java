package se.chapter02.thread.synchronize;

/**
 * @ClassName: Synchronize
 * @Description: TODO
 * 对于两个线程相互协作完成任务，同步锁已经不能很好地解决
 **/
public class Synchronize {
    public static void main(String[] args) {
        Resource r = new Resource();
        Thread t1 = new Thread(new Input(r));
        Thread t2 = new Thread(new Output(r));

        t1.start();
        t2.start();
    }
}

class Resource{
   public String name;
    public String sex;
}
class Input implements Runnable{
    private Resource r;
    public Input(Resource r){
        this.r = r;
    }
    @Override
    public void run() {
        int x = 1;
        while (true){
            synchronized (Output.class){
                if(x%2 == 1){
                    r.name = "mike";
                    r.sex = "男";
                }else{
                    r.name = "lili";
                    r.sex = "女";
                }
            }
            x++;
        }
    }
}

class Output implements Runnable{

    private Resource r;
    public Output(Resource r){
        this.r = r;
    }
    @Override
    public void run() {
        while (true){
            synchronized (Output.class) {
                System.out.println(r.name + "..............." + r.sex);
            }
        }
    }
}
