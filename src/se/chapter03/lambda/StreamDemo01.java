package se.chapter03.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/24 8:48
 * @Version 1.0
 **/
/**
 * 一、Stream 的三个操作步骤
 *
 *  1、创建 Stream
 *
 *  2、中间操作
 *
 *  3、终止操作（终端操作）
 *
 */
public class StreamDemo01 {

    /*
     * @Autor: 13528
     * @Description: 演示创建流的4种方式
     * @Date: 9:01 2019/11/24
     * @Param: []
     * @return: void
     **/
    @Test
    public void test01(){
        //1.可以通过 Collection 系列集合提供的 stream() 或 paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过 Arrays 中的静态方法 stream() 获取数组流
        Employe[] emps = new Employe[10];
        Stream<Employe> stream2 = Arrays.stream(emps);

        //3.通过 Stream 类中的静态方法 of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(10).forEach(System.out::println);
    }
    List<Employe> emps = Arrays.asList(
            new Employe("张三", 18,9999.99),
            new Employe("李四", 38,5555.99),
            new Employe("王五", 50,6666.66),
            new Employe("王五", 50,6666.66),
            new Employe("王五", 50,6666.66),
            new Employe("赵六", 16,3333.33),
            new Employe("aa", 10,777),
            new Employe("ab", 10,7777.77)
    );
    /*中间操作
    //内部迭代由 StreamAPI 完成
    /**
     * 筛选与切片
     * filter —— 接收 Lambda，从流中排除某些元素；
     * limit —— 截断流，使其元素不超过给定数量；
     * skip(n) —— 跳过元素，返回一个仍掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补；
     * distinct —— 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素。
     */
    @Test
    public void test02(){
        emps.stream().filter(e->e.getAge()>18)
                //.skip(1)
                .distinct()
                .forEach(System.out::println);
    }

}
