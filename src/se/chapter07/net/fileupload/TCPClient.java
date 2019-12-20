package se.chapter07.net.fileupload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @ClassName: TCPClient
 * @Description: TODO

 **/
public class TCPClient {
    public static void main(String[] args) throws IOException {
        //1.
        Socket socket = new Socket("127.0.0.1",8888);
        //2.
        InputStream fis = new FileInputStream("D:\\IOTest\\111.mp4");
        //3.
        byte[] buf = new byte[1024*1024*2];
        OutputStream out = socket.getOutputStream();
        int len = 0;
        while (-1 != (len = fis.read(buf))){
            out.write(buf,0,len);
        }
        System.out.println("上传完毕！");
        //结束标志并没有写进去，用次函数给服务器追加一个技术标记
        /*
         * shutdownOutput()
         * 此方法使用后不能再使用输出流否则会报i/o异常
         **/
        socket.shutdownOutput();
        //out.write("jieshu".getBytes());
        //4.
        InputStream in = socket.getInputStream();
        len = in.read(buf);
        System.out.println(new String(buf,0,len));;

        //5.
        fis.close();
        socket.close();
    }
}
