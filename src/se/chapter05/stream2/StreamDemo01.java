package se.chapter05.stream2;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: StreamDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/26 9:45
 * @Version 1.0
 **/
public class StreamDemo01 {
    //测试文件输出流
    @Test
    public void test01() throws IOException {
        OutputStream outputStream = new FileOutputStream("D:\\aa.txt",true);
        String str = "\nhello world";
        outputStream.write(str.getBytes());
        outputStream.close();
    }

}
