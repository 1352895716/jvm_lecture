package se.chapter05.stream2.writer;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: FileWriterDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/28 16:58
 * @Version 1.0
 **/
public class FileWriterDemo01 {

    private static final String LINE_SPERATOR = System.getProperty("line.separator");

    @Test
    public void test01() throws IOException {
        FileWriter fw = new FileWriter("D:\\aa2.txt",true);
        //fw.write("aaaaaaa\r\naaaaaaaa");
        //下面语句在两种系统上都可以执行，且不需要修改代码
        fw.write("aaaaaaa"+LINE_SPERATOR+"aaaaaaaa");
        fw.close();
    }

    //需求：从硬盘文件中读取在硬盘输出（以单个字符形式读取）
    @Test
    public void test02() throws IOException {
        Reader reader = new FileReader("D:\\aa2.txt");
        int ch = 0;
        while (-1 != (ch = reader.read())){
            System.out.println((char)ch);
        }
        reader.close();
    }
    //需求：从硬盘文件中读取在硬盘输出（以数组方式读取）
    @Test
    public void test03() throws IOException {
        Reader reader = new FileReader("D:\\aa2.txt");
        char[] buf = new char[50];//这个就是缓冲区，缓冲流其实就是对缓冲数组的包装
        int len = 0;
        while (-1 != (len = reader.read(buf))){
            System.out.println(new String(buf,0,len));
        }
        reader.close();
    }

    //需求：从d盘文件中复制到d盘中
    @Test
    public void test04() throws IOException {
        //1.读取一个文本文件，使用字符流和文件想关联
        Reader reader = new FileReader("D:\\aa2.txt");
        //2.创建一个目的，用于存储读到的数据
        FileWriter fw = new FileWriter("D:\\aa3.txt");
        char[] buf = new char[10];
        int len = 0;
        //3.频繁的读写操作
        while (-1 != (len = reader.read(buf))){
            fw.write(buf,0,len);
        }
        //4.关闭流资源
        fw.close();
        reader.close();
    }


}
