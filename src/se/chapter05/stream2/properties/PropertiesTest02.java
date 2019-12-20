package se.chapter05.stream2.properties;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName: PropertiesTest02
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/1 16:14
 * @Version 1.0
 **/
public class PropertiesTest02 {
    public static void main(String[] args) throws IOException {
        getCount();
    }

    /*
     * @Autor: 13528
     * @Description: TODO
     * 需求：定义功能，获取一个程序运行的次数，如果大于5次，则需要注册
     * 解决方案：使用配置文件记录次数
     *
     **/
    private static void getCount() throws IOException {
        File file = new File("count.properties");
        if(!file.exists()){
            file.createNewFile ();
        }
        FileInputStream fis = new FileInputStream(file);
        Properties pro = new Properties();
        pro.load(fis);

        String time =  pro.getProperty("time");
        int count = 0;
        if(time!=null){
            count = Integer.parseInt(time);
            if(count==5){
                throw new RuntimeException("免费次数已使用完毕,请注册！！！");
            }
        }
        count++;
        pro.setProperty("time",count+"");
        FileOutputStream fos = new FileOutputStream(file);
        pro.store(fos,"info");

        fos.close();
        fis.close();

    }


}
