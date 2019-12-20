package se.chapter05.stream2.writer;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: BufferedWriterDemo
 * @Description: 用于测试缓冲流、
 *                1.缓冲流的出现就是提高对数据的读写效率
 *                2.使用时必须结合流对象（）
 * @Autor:13528
 * @Date: 2019/11/29 10:33
 * @Version 1.0
 **/
public class BufferedWriterDemo {

    @Test
    public void test01() throws IOException {
        FileWriter fw = new FileWriter("D:\\aa1.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        //使用缓冲区的写入方法先将数据写入缓冲区中
        bw.write("aaaaaaaaaaaaabbbbbbbbbbbbbbb");
        bw.newLine();//写入一个换行符
        bw.write("cccccccccccccccdddddddddddd");
        //使用缓冲区的刷新方法将数据刷新到目的地
        //bw.flush();
        bw.close();//缓冲区其实就是封装了一个数组，它的关闭就是对其构造对象的关闭
    }

    @Test
    public void test02() throws IOException {
        FileReader fr = new FileReader("D:\\aa1.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while (null != (line = br.readLine())){
            System.out.println(line);
        }
        br.close();
    }

   /*
    * @Autor: 13528
    * @Description: 测试自定义的缓冲流
    * @Date: 16:47 2019/11/29
    * @Param: []
    * @return: void
    **/
    @Test
    public void test04() throws IOException {
        FileReader fr = new FileReader("D:\\aa1.txt");
        MyBufferedReader mbr = new MyBufferedReader(fr);
        String line = null;
        while (null != (line = mbr.myReadLine())){
            System.out.println(line);
        }
        mbr.myClose();
    }

    //测试bufferedReader与bufferedWriter的使用
    @Test
    public void test03() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\aa1.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\aa3.txt",true));

        String line = null;
        while (null != (line = br.readLine())){
            //使用缓冲区的写入方法先将数据写入缓冲区中
            bw.write(line);
            bw.newLine();//写入一个换行符
            //使用缓冲区的刷新方法将数据刷新到目的地
            bw.flush();//没操作一次就刷新一次
        }
        bw.close();
        br.close();
    }

    /*
     * @Autor: 13528
     * @Description: 测试LineNumberWriter
     * @Date: 20:24 2019/11/29
     * @Param: []
     * @return: void
     **/
    @Test
    public void test05() throws IOException {
        FileReader fr = new FileReader("D:\\aa1.txt");
        LineNumberReader lnr = new LineNumberReader(fr);
        String line = null;

        lnr.setLineNumber(8);
        while (null != (line = lnr.readLine())){
            System.out.println(lnr.getLineNumber()+":"+line);
        }
    }


}
