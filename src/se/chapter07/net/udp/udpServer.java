package se.chapter07.net.udp;




import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName: udpServer
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/6 16:30
 * @Version 1.0
 **/
public class udpServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("接收方启动中！");
        //1.使用DatagramSocket 指定端口，创建客户端、
        DatagramSocket server = new DatagramSocket(6666);
        //2.准备容器 封装成DatagramPacket包裹
        byte[] container = new byte[1024*60];
        DatagramPacket packet = new DatagramPacket(container,0,container.length);
        //3.阻塞式接受包裹receive（DatagramPacket p）
        server.receive(packet);
        //4.分析数据
        byte[] datas = packet.getData();
        System.out.println(new String(datas,0,datas.length));
       /* DataInputStream dis = new DataInputStream(new ByteArrayInputStream(datas));

        String msg = dis.readUTF();
        int v = dis.readInt();
        boolean b = dis.readBoolean();
        System.out.println(msg+" "+v+" "+b);*/
       //4.验证对象类型的数据传输
       /* ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(datas));
        Object p1  = ois.readObject();
        Object p2  = ois.readObject();
        System.out.println(p1);
        System.out.println(p2);*/
       //验证图片上传
        ByteArrayInputStream bais = new ByteArrayInputStream(datas);
        FileOutputStream fos = new FileOutputStream("D:\\1.jpg");
        int len = 0;
        byte[] buf = new byte[1024];
        while (-1 != (len = bais.read(buf))){
            fos.write(buf,0,len);
        }
        //5.
        server.close();
    }

}
