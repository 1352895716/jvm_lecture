package se.chapter05.stream2.bytestream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName: ByteStreamDemo
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/29 21:17
 * @Version 1.0
 **/
public class ByteStreamDemo {

    /*
     * @Autor: 13528
     * @Description: 测试字节输入流的几种读取方式
     * @Date: 21:17 2019/11/29
     * @Param: []
     * @return: void
     **/
    //1. read()
    @Test
    public void test01() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\aa1.txt");
        int len = 0;
        while (-1 != (len = fis.read())){
            System.out.println(len);
        }
        fis.close();
    }
    //2. 是用缓冲
    @Test
    public void test02() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\aa1.txt");
        byte[] buf = new byte[1024];
        int len = 0;
        while (-1 != (len = fis.read(buf))){
            System.out.println(new String(buf,0,len));
        }
        fis.close();
    }
    //3.
    @Test
    public void test03() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\aa1.txt");
        byte[] buf = new byte[fis.available()];//available()函数要慎用，文件过大会内存溢出
        int len = 0;
        while (-1 != (len = fis.read(buf))){
            System.out.println(new String(buf));
        }
        fis.close();
    }
}
