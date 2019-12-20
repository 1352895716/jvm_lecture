package se.chapter05.stream2.print;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: PrintTest01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/1 17:05
 * @Version 1.0
 **/
public class PrintTest01 {
    /*
     * @Autor: 13528
     * @Description: TODO
     * PrintStram:
     * 1.提供了打印方法可以对多种数据类型值进行打印，并保持数据的表示形式
     * 2.他不抛IOException
     *
     * 构造函数，接受三种类型的值
     * 1.字符串路径
     * 2.file对象
     * 3.字节输出流
     **/
    @Test
    public void test01() throws FileNotFoundException {
        PrintStream ps = new PrintStream("aaa.txt");

        ps.write(97);//只打印int型的低八位
        //ps.print(97);
        ps.close();
    }

    public static void main(String[] args) throws IOException {
        test02();
    }
    /*
     * @Autor: 13528
     * PrintWriter
     * 1.字符串路径
     * 2.file对象
     * 3.字节输出流
     * 4.字符输出流
     **/
    public static void test02() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out,true);
        String line = null;
        while (null != (line = br.readLine())){
            if(line.equals("over"))
                break;
            pw.println(line.toUpperCase());
            //pw.flush();
        }
        pw.close();
        br.close();
    }

}
