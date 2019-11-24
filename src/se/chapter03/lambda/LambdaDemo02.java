package se.chapter03.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @ClassName: LambdaDemo02
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/23 11:55
 * @Version 1.0
 **/
public class LambdaDemo02 {

    @Test
    public void test01(){
        String str = "hello world";
        String str2 = testLambda(str,s->s.toUpperCase());
        System.out.println(str2);
        str2 = testLambda(str,s->s.substring(2,4));
        System.out.println(str2);
    }
    public String testLambda(String s, Function<String,String>fun){
        return fun.apply(s);
    }

    List<Employe> emps = Arrays.asList(
            new Employe("张三", 18,9999.99),
            new Employe("李四", 38,5555.99),
            new Employe("王五", 50,6666.66),
            new Employe("赵六", 16,3333.33),
            new Employe("aa", 10,777),
            new Employe("ab", 10,7777.77)
    );

    @Test
    public void test02(){
        Collections.sort(emps,(x,y)->{
            if(x.getAge()==y.getAge()){
                //return x.getName().compareTo(y.getName());
                return new Double(x.getSalary()-y.getSalary()).intValue();
            }
            return x.getAge()-y.getAge();
        });
        emps.forEach(System.out::println);
    }


    @Test
    public void test03(){
        Long l = testLambda03(10L,20L,s->s);
        System.out.println(l);
    }
    public Long testLambda03(long l1,long l2,Function<Long,Long>fun){
        return fun.apply(l1)+fun.apply(l2);
    }
}
