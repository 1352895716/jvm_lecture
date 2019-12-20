package se.chapter07.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName: TalkRecive
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/7 9:41
 * @Version 1.0
 **/
public class TalkRecive implements Runnable{
    DatagramSocket server = null;
    int port;
    public TalkRecive(int port){
        this.port = port;
        try {
            server = new DatagramSocket(this.port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                //2.准备容器 封装成DatagramPacket包裹,每次接受都需要封装一个容器
                byte[] container = new byte[1024];
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
