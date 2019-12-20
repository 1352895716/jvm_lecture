package se.chapter07.net.udp;


import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @ClassName: udpServer
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/6 16:30
 * @Version 1.0
 **/
public class UDPTalkServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("接收方启动中！");
        //1.使用DatagramSocket 指定端口，创建客户端、
        DatagramSocket server = new DatagramSocket(6666);


        while (true){
            //2.准备容器 封装成DatagramPacket包裹
            byte[] container = new byte[1024*60];
            DatagramPacket packet = new DatagramPacket(container,0,container.length);
            //3.阻塞式接受包裹receive（DatagramPacket p）
            server.receive(packet);
            //4.分析数据
            byte[] datas = packet.getData();
            String str = new String(datas,0,datas.length);
            System.out.println(str);
            if("byte".equals(str.trim()))
                break;
        }

        //5.
        server.close();
    }

}
