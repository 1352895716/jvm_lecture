package se.chapter07.net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @ClassName: TalkSend
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/7 9:41
 * @Version 1.0
 **/
public class TalkSend implements Runnable{
    DatagramSocket client = null;
    DatagramPacket packet = null;
    String toIP;
    int toPort;
    int port;
    public TalkSend(int port,String toIP,int toPort){
        try {
            this.port = port;
            this.toIP = toIP;
            this.toPort = toPort;
            client = new DatagramSocket(this.port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                //2.构造要被打包的字节数组
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                byte[] datas = str.getBytes();
                //3.封装成DatagramPacket包裹，需要指定目的地//没发送一次数据就要构建一个packet对象
                packet = new DatagramPacket(datas,0,datas.length,
                        new InetSocketAddress(this.toIP,this.toPort));
                //4.发送包裹
                client.send(packet);
                if("byte".equals(str))
                    break;
            }
            //5.释放资源
            client.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
