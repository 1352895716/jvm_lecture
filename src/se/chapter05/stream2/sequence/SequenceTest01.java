package se.chapter05.stream2.sequence;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @ClassName: SequenceTest01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/2 8:55
 * @Version 1.0
 **/
public class SequenceTest01 {

    /*
     * @Autor: 13528
     * SequenceInputStream
     * 表示其他输入流的逻辑串联。它从输入流的有序集合开始，并从第一个输入流开始读取，
     * 直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
     **/
    @Test
    public void test01() throws IOException {
        Vector<FileInputStream> v = new Vector<>();
        v.add(new FileInputStream("D:\\IOTest\\1.txt"));
        v.add(new FileInputStream("D:\\IOTest\\2.txt"));
        v.add(new FileInputStream("D:\\IOTest\\3.txt"));

       /* 可用下面的程序进行改进
        List<FileInputStream> al = new ArrayList<>();
        al.add(new FileInputStream("D:\\IOTest\\1.txt"));
        al.add(new FileInputStream("D:\\IOTest\\2.txt"));
        al.add(new FileInputStream("D:\\IOTest\\3.txt"));
        Enumeration<FileInputStream> en = Collections.enumeration(al);*/

        Enumeration<FileInputStream> en = v.elements();

        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\4.txt");
        byte[] buf = new byte[1024];

        int len = 0;
        while (-1 != (len = sis.read(buf))){
            fos.write(buf,0,len);
        }
        fos.close();
        sis.close();

    }

    //Vector效率太低，使用ArrayList对上面的程序进行改进
    @Test
    public void test02() throws IOException {
        List<FileInputStream> al = new ArrayList<>();
        al.add(new FileInputStream("D:\\IOTest\\1.txt"));
        al.add(new FileInputStream("D:\\IOTest\\2.txt"));
        al.add(new FileInputStream("D:\\IOTest\\3.txt"));
        Enumeration<FileInputStream> en = Collections.enumeration(al);

        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\1234.txt");
        byte[] buf = new byte[1024];

        int len = 0;
        while (-1 != (len = sis.read(buf))){
            fos.write(buf,0,len);
        }
        fos.close();
        sis.close();

    }

    //文件分割
    @Test
    public void test03() throws IOException {
        //1.关联将要分割的目标文件
        FileInputStream fis = new FileInputStream("D:\\IOTest\\111.mp4");
        byte[] buf = new byte[1024*1024*2];//每次分割的大小
        //2.指定存放分割文件的目录，若不存在则创建
        File dir = new File("D:\\IOTest\\partFiles");
        if(!dir.exists())
            dir.mkdirs();
        FileOutputStream fos = null;
        int len = 0;
        int count = 1;
        //3.循环写入即可
        while (-1 != (len = fis.read(buf))){
           fos = new FileOutputStream(new File(dir,count+".part"));
           fos.write(buf,0,len);
           count++;
           fos.close();
        }
        //4.关闭流
        fis.close();

    }

    //文件合并
    @Test
    public void test04() throws IOException {

        List<FileInputStream>al = new ArrayList<>();
        File f = new File("D:\\IOTest\\partFiles");
        File[] files = f.listFiles((x)->x.getName().endsWith(".part"));
        for (File file:files) {
            System.out.println(file.getName());
            al.add(new FileInputStream(file));
        }
        Enumeration<FileInputStream>en = Collections.enumeration(al);

        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\megerFile\\222.mp4");

        byte[] buf = new byte[1024];
        int len = 0;

        while (-1 != (len = sis.read(buf))){
            fos.write(buf,0,len);
        }
        fos.close();
        sis.close();
    }

    //文件分割+配置文件
    @Test
    public void test05() throws IOException {
        //1.关联源文件
        FileInputStream fis = new FileInputStream("D:\\IOTest\\111.mp4");
        byte[] buf = new byte[1024*1024*2];
        //2.创建存放分割文件目录
        File dir = new File("D:\\IOTest\\partFiles");
        FileOutputStream fos = null;
        int len = 0;
        int count = 1;
        //3.循环创建文件，并写入数据
        while (-1 != (len = fis.read(buf))){
            fos = new FileOutputStream(new File(dir,count+".part"));
            fos.write(buf,0,len);
            fos.close();
            count++;
        }
        //4.创建配置文件保存分割数据
        fos = new FileOutputStream(new File(dir,count+".properties"));
        Properties pro = new Properties();
        pro.setProperty("filename","111.mp4");
        pro.setProperty("count",count+"");
        pro.setProperty("dir",dir.getAbsolutePath());
        pro.store(fos,"save");

        fos.close();
        fis.close();
    }

    //文件合并+配置文件
    @Test
    public void test06()throws IOException{
        File dir = new File("D:\\IOTest\\partFiles");
        Properties pro = new Properties();
        File[] files = dir.listFiles(x->x.getName().endsWith(".properties"));
        if(files.length != 1)
            throw new RuntimeException("配置文件个数错误");
        pro.load(new FileInputStream(files[0]));
        //取出配置文件中保存的信息
        int count = Integer.parseInt(pro.getProperty("count"));
        String filename = pro.getProperty("filename");

        File[] files2 = dir.listFiles(x->x.getName().endsWith(".part"));
        if(count != files2.length+1)
            throw new RuntimeException("分割文件数目错误！");

        //开始创建序列流合并文件对象
        //1.目的
        FileOutputStream fos = new FileOutputStream("D:\\IOTest\\megerFile"+filename);
        //构造序列流对象
        List<FileInputStream>al = new ArrayList<>();
        for (int i=1;i<count;i++){
            al.add(new FileInputStream(new File(dir,i+".part")));

        }
        /*for (File file:files2) {
            System.out.println(file.getName());
            al.add(new FileInputStream(file));
        }*/
        Enumeration<FileInputStream>en = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(en);

        //开始合并
        int len = 0;
        byte[] buf = new byte[1024];
        while (-1 != (len = sis.read(buf))){
            fos.write(buf,0,len);
        }
        sis.close();
        fos.close();
    }
}
