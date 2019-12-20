package se.chapter07.net.bs_tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: TCPServer
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/5 22:35
 * @Version 1.0
 **/
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();
        /*byte[] buf = new byte[1024];
        int len = 0;
        while (-1 != (len = is.read(buf))){
            System.out.println(new String(buf,0,len)+"      111");
        }*/
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //从请求中获得html页面的地址
        String line = br.readLine();
        String[] arr = line.split(" ");
        String htmlPath = arr[1].substring(13);
        //
        OutputStream out = socket.getOutputStream();
        //返回页面前的三行固定格式
        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Content-Type:text/html\r\n".getBytes());
        out.write("\r\n".getBytes());
        System.out.println(htmlPath);
        FileInputStream fis = new FileInputStream(htmlPath);

        byte[] buf = new byte[1024];
        int len = 0;
        while (-1 != (len = fis.read(buf))){
            out.write(buf,0,len);
        }

        //释放资源
        fis.close();
        socket.close();
        serverSocket.close();

    }
}
