package se.chapter07.net.udp;

import se.chapter05.stream2.objectstream.Person;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @ClassName: UDPClient
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/6 16:38
 * @Version 1.0
 **/
public class UDPClient {
    public static void main(String[] args) throws IOException {

        System.out.println("发送方启动中。。。。");
        //1.使用DatagramSocket 指定端口，创建发送端
        DatagramSocket client = new DatagramSocket(7777);
        //2.准备数据
        //byte[] datas = "hello server".getBytes();
        //2.验证下其他类型的数据
       /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF("hello world");
        dos.writeInt(12);
        dos.writeBoolean(true);
        byte[] datas = baos.toByteArray();*/
       //2.验证对象
        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new Person("zlp",18));
        oos.writeObject(new Person("zdxf",18));
        byte[] datas = baos.toByteArray();*/
        //验证udp图片上传
        FileInputStream fis = new FileInputStream("C:\\Users\\13528\\Pictures\\Saved Pictures\\1.jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        while (-1 != (len = fis.read(buf))){
            baos.write(buf,0,len);
        }
        byte[] datas = baos.toByteArray();
        //3.封装成DatagramPacket包裹，需要指定目的地
        DatagramPacket packet = new DatagramPacket(datas,0,datas.length,
                new InetSocketAddress("127.0.0.1",6666));
        //4.发送包裹
        client.send(packet);
        //5.释放资源
        client.close();
        //System.gc();//底层调用的Rnutime类中的gc()，
    }
}
