package se.concurren.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: parallelStreamAPIDemo
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/15 12:27
 * @Version 1.0
 **/
public class parallelStreamAPIDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        for (int i=1;i<=200000;i++)
            list.add(i);

        long start = System.currentTimeMillis();
        list.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();

        System.out.println(end - start);

        start = System.currentTimeMillis();
        list.parallelStream().forEach(parallelStreamAPIDemo::isPrime);
        end = System.currentTimeMillis();

        System.out.println(end - start);

    }


    static boolean isPrime(int num){
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i == 0)
                return false;
        }
        return true;
    }
}
