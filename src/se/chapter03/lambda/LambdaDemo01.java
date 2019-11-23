package se.chapter03.lambda;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName: LambdaDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/22 20:30
 * @Version 1.0
 **/
public class LambdaDemo01 {

    /*
     * @Autor: 13528
     * @Description: 测试 Consumer<T>消费型接口
     * @Date: 9:10 2019/11/23
     * @Param: []
     * @return: void
     **/
     @Test
    public void test01(){
        testConsumer(1000,m -> System.out.println("消费了"+m+"元"));
     }
     public void testConsumer(double m,Consumer<Double>con){
        con.accept(m);
     }
     /*
      * @Autor: 13528
      * @Description: 测试 Supplier<T>供给型接口
      * @Date: 9:31 2019/11/23
      * @Param: []
      * @return: void
      **/
     @Test
    public void test02(){
        List<Integer>list = testSupplier(10,()->(int)(Math.random()*100));
         for (Integer i:list) {
             System.out.println(i);
         }
     }
     public List<Integer> testSupplier(int n, Supplier<Integer>sup){
        List<Integer>list = new ArrayList<>();
        int m = 0;
        for(int i=0;i<n;i++){
            m = sup.get();
            list.add(m);
        }
        return list;
     }

     /*
      * @Autor: 13528
      * @Description: 测试 Function<T>函数型接口
      * @Date: 9:40 2019/11/23
      * @Param: []
      * @return: void
      **/
     @Test
    public void test03(){
         String newStr = testFunction("asdasd",s->s.toUpperCase());
         System.out.println(newStr);
     }
     public String testFunction(String str, Function<String,String>fun){
        return fun.apply(str);
     }

     /*
      * @Autor: 13528
      * @Description: 测试 Predict<T>断言型接口
      * @Date: 9:56 2019/11/23
      * @Param: []
      * @return: void
      **/
     @Test
    public void test04(){
         List<String>list = Arrays.asList("AA","ssss","hello","LOVE");
         List<String>list2 = testPredicate(list,str->str.length()>2);
        for (String str:list2) {
            System.out.println(str);
        }
    }

     public List<String> testPredicate(List<String> list, Predicate<String> pre){
         List<String>newList = new ArrayList<>();
         for (String str:list) {
             if(pre.test(str));
             newList.add(str);
         }
         return newList;
     }
}
