package se.chapter05.stream2.bytestream;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: ByteCopyTest
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/30 9:25
 * @Version 1.0
 **/
public class ByteCopyTest {

    /*
     * @Autor: 13528
     * @Description: 测试字节流复制文件
     * @Date: 9:43 2019/11/30
     * @Param: []
     * @return: void
     **/
    @Test
    public void test01() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\IOTest\\3.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\4.jpg");
        byte[] buf = new byte[1024];//相当于自定义了一个缓冲区
        int len = 0;
        while (-1 != (len = fis.read(buf))){
            fos.write(buf);
        }
        fos.close();
        fis.close();


    }

    @Test
    public void test02() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\IOTest\\3.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\IOTest\\5.jpg"));
        int len = 0;

        while (-1 != (len = bis.read())){//使用缓冲流和自定义缓冲区都是为了提高读写效率
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }
    @Test
    public void test03() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\IOTest\\3.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\6.jpg");
        byte[] buf = new byte[fis.available()];//创建正好是文件大小的数组，不用循环了
        fis.read(buf);
        fos.write(buf);
        fos.close();
        fis.close();
    }
    @Test//强烈建议不使用此种方式
    public void test04() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\IOTest\\3.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\7.jpg");

        int len = 0;//效率最低
        while (-1 != (len = fis.read())){
            fos.write(len);
        }
        fos.close();
        fis.close();
    }
    //测试从键盘输入，从控制台输出
    public static void main(String[] args) throws IOException {
        test07();
    }
    //使用转换流将字节流转成字符流读取并打印
    public static void test07() throws IOException {
        //字节流
        /*InputStream in = System.in;
        //转换流将字节流转成字符流
        InputStreamReader isr = new InputStreamReader(in);
        //
        BufferedReader br = new BufferedReader(isr);*/

       /* OutputStream out = System.out;
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(osw);*/
       /*
        * 需求1.将从键盘录入的数据写入到文件中
        *    只需将System.out改成new FileOutputStream("D:\\IOTest\\a1.txt")即可
        *
        * 需求2.从文件中读取数据，写到控制台上
        *    只需将System.in改成new FileInputStream("D:\\IOTest\\a1.txt")即可
        *
        **/

       //读写的通用格式
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\IOTest\\a1.txt")));

        String buf = null;
        while(null != (buf = br.readLine())){
            if(buf.equals("over")){
                //System.out.println("程序结束！");
                bw.write("程序结束！");
                bw.newLine();
                bw.flush();
                break;
            }
            //System.out.println(buf.toUpperCase());
            bw.write(buf.toUpperCase());
            bw.newLine();
            bw.flush();

        }


    }


    //测试从键盘输入，从控制台输出，遇到over结束

    public static void test05() throws IOException {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while(-1 != (len = in.read())){
            if(len == '\r')
                continue;
            if(len == '\n'){
                if(sb.toString().equals("over")) {//此处要先转成String,因为Stringbuilder没有重写equals方法
                    System.out.println("程序结束！");
                    break;
                }
                System.out.println(sb.toString().toUpperCase());
                sb.delete(0,sb.length());
            }else {
                sb.append((char)len);
            }

        }

    }

    public void test06() throws IOException {
        InputStream in = System.in;
        int ch = in.read();
        System.out.println(ch);
        int ch1 = in.read();
        System.out.println(ch1);
        int ch2 = in.read();
        System.out.println(ch2);

        //in.close();in不需要关闭，会随着系统消失，单关闭后，系统里的其他in对象也不能使用
    }

    //
    @Test
    public void test08() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\IOTest\\a1.txt"),"utf-8"));
        bw.write("集合无法开机后部分会计师");
        bw.flush();
        bw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\IOTest\\a1.txt")));
        String line = br.readLine();
        System.out.println(line);
        br.close();
    }
}
