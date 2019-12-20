package se.chapter05.stream2.properties;

import org.junit.Test;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * @ClassName: PropertiesTest01
 * Map
 *  \--HashTable
 *      \--Properties
 * Properties集合
 * 特点：
 * 1.该集合的建和值都是字符串类型
 * 2.集合中的数据可以保持到流中或从流中获取
 *
 * 通常该集合用于操作键值对形式存在的配置文件
 **/
public class PropertiesTest01 {

    //集合的存取
    @Test
    public void test01(){
        Properties pro = new Properties();
        //存
        pro.setProperty("zhangsan","30");
        pro.setProperty("lisi","32");
        pro.setProperty("wangwu","36");
        pro.setProperty("zhakoliu","20");
        //获取集合中所有的建
        Set<String> set = pro.stringPropertyNames();

        //修改元素
        pro.setProperty("lisi","48");
        for (String str:set) {
            String value = pro.getProperty(str);
            System.out.println(str+":"+value);
        }
    }

    //演示properties和流对象相结合的功能
    //1.
    @Test
    public void test02(){
        Properties pro = System.getProperties();
        pro.list(System.out);
    }
    //2.将数据持久化到硬盘上
    @Test
    public void test03() throws IOException {
        Properties pro = new Properties();
        //存
        pro.setProperty("zhangsan","30");
        pro.setProperty("lisi","32");
        pro.setProperty("wangwu","36");
        pro.setProperty("zhakoliu","20");
        FileOutputStream fos = new FileOutputStream("info.properties");
        pro.store(fos,"info");
        fos.close();//使用流后一定要记得关闭
    }
    @Test
    public void test04(){
        File f = new File("aa.txt");//手动删不掉，就用程序删
        f.delete();
    }

   //从流中获取数据
    @Test
    public void test05() throws IOException {
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream("info.properties");
        FileReader fr = new FileReader("info.properties");
        pro.load(fr);
        pro.list(System.out);
        fr.close();
        fis.close();
    }

    //定义一个load方法
    @Test
    public void myLoad() throws IOException {
        Properties pro = new Properties();

        //--------------------------------------------------------------------------------
        //这一段就相当于load方法
        BufferedReader br = new BufferedReader(new FileReader("info.properties"));
        String line = null;
        while (null != (line = br.readLine())){
            if(line.startsWith("#"))
                continue;
            String[] arr = line.split("=");
            pro.setProperty(arr[0],arr[1]);
        }
        //---------------------------------------------------------------------------------------
        pro.list(System.out);

        br.close();//关闭流 一定要记得
    }

    //修改配置文件
    //1.首先读取文件
    //2.加载进集合
    //3.利用集合对文件进行操作
    @Test
    public void test() throws IOException {
        File f = new File("info.properties");
        if(!f.exists()){
            f.createNewFile();
        }
        //1.首先读取文件
        FileInputStream fis = new FileInputStream(f);
        Properties pro = new Properties();
        //2.加载进集合
        pro.load(fis);
        //3.利用集合对文件进行操作
        pro.setProperty("lisi","16");
        //4.在重新写入文件
        FileOutputStream fos = new FileOutputStream(f);
        pro.store(fos,"update info");
        fos.close();
        fis.close();
    }
}
