package se.chapter05.stream2.objectstream;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: ObjectStreamTest01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/3 9:01
 * @Version 1.0
 **/
public class ObjectStreamTest01 {

    //测试ObjectOutputStream
    @Test
    public void test01() throws IOException, ClassNotFoundException {
        //writeObj();
        readObj();
    }

    //从硬盘反序列化对象
    private void readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\IOTest\\obj.object"));
        Person p1 = (Person) ois.readObject();
        Person p2 = (Person) ois.readObject();
        System.out.println(p1);
        System.out.println(p2);
        ois.close();

    }

    //将对象序列化到硬盘上
    private void writeObj() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\IOTest\\obj.object"));
        oos.writeObject(new Person("zlp",18));
        oos.writeObject(new Person("zdxf",18));
        oos.close();
    }


}
