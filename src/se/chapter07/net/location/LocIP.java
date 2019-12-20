package se.chapter07.net.location;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: LocIP
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/6 10:53
 * @Version 1.0
 **/
public class LocIP {
    public static void main(String[] args) throws UnknownHostException {
        //1.
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostName());
        System.out.println(addr.getHostAddress());
        System.out.println("-------------------------------");
        //2.
       addr = InetAddress.getByName("www.baidu.com");
        System.out.println(addr.getHostName());
        System.out.println(addr.getHostAddress());//DNS：将ip地址转成字符串
        System.out.println("-------------------------------");



    }
}
