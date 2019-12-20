package se.chapter07.net.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @ClassName: UDPClient
 * @Description: TODO
 * 需求：在线咨询
 * 第一步单向咨询
 * //1.使用DatagramSocket 指定端口，创建发送端
 * //2.构造要被打包的字节数组
 * //3.封装成DatagramPacket包裹，需要指定目的地
 * //4.发送包裹
 * //5.释放资源
 **/
public class UDPTalkClient {
    public static void main(String[] args) throws IOException {

        System.out.println("发送方启动中。。。。");
        //1.使用DatagramSocket 指定端口，创建发送端
        DatagramSocket client = new DatagramSocket(7777);

        while (true){
            //2.构造要被打包的字节数组
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = br.readLine();
            byte[] datas = str.getBytes();
            //3.封装成DatagramPacket包裹，需要指定目的地
            DatagramPacket packet = new DatagramPacket(datas,0,datas.length,
                    new InetSocketAddress("127.0.0.1",6666));
            //4.发送包裹
            client.send(packet);
            if("byte".equals(str))
                break;
        }
        //5.释放资源
        client.close();

    }
}
