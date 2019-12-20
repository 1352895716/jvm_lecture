package se.concurren;

/**
 * @ClassName: TestDemo03
 * @Description: 同步方法和非同步方法是否可以同时调用,可以正因为可以所以可能会出现脏读问题
 * 业务写方法枷锁
 * 业务读方法不加锁
 * 容易出现脏读问题（即读到还没写之前的数据）
 **/
public class TestDemo03 {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account();
        new Thread(()->a.set("lisi",1000)).start();
        //Thread.sleep(1000);

        System.out.println(Thread.currentThread().getName()+" "+a.get());
        //new Thread(()->a.get("lisi")).start();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+" "+a.get());
        //new Thread(()->a.get("lisi")).start();
    }
}
class Account{
    private String name;
    private double balance;

    public synchronized void set(String name,double balance){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

   /* public   synchronized void get(String name){
        System.out.println(Thread.currentThread().getName()+"   "+this.balance);
    }*/
    public synchronized double get(){
        return this.balance;
    }
}
