package se.chapter07.net.fileupload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @ClassName: TCPServer
 * @Description: TODO

 **/
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //1.
        ServerSocket serverSocket = new ServerSocket(8888);


        while (true){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try{
                    //3.
                    InputStream in = socket.getInputStream();
                    //4.
                    String fileName = "D:\\zlp"+System.currentTimeMillis()+ new Random().nextInt(999999)+".jpg";
                    FileOutputStream fos = new FileOutputStream(fileName);
                    //5.
                    byte[] buf = new byte[1024*1024*2];
                    int len = 0;
                    while (-1 != (len = in.read(buf))){
                        /*if(len != 1024){
                            fos.write(buf,0,len);
                            break;
                        }*/
                        fos.write(buf,0,len);
                    }
                    System.out.println("写入成功！");
                    //6.
                    socket.getOutputStream().write("上传成功！".getBytes());

                    fos.close();
                    socket.close();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }).start();
        }
        //2.
       /* while (true){
            Socket socket = serverSocket.accept();
            //3.
            InputStream in = socket.getInputStream();
            //4.
            String fileName = "D:\\zlp"+System.currentTimeMillis()+ new Random().nextInt(999999)+".jpg";
            FileOutputStream fos = new FileOutputStream(fileName);
            //5.
            byte[] buf = new byte[1024*1024*2];
            int len = 0;
            while (-1 != (len = in.read(buf))){
            *//*if(len != 1024){
                fos.write(buf,0,len);
                break;
            }*//*
                fos.write(buf,0,len);
            }
            System.out.println("写入成功！");
            //6.
            socket.getOutputStream().write("上传成功！".getBytes());

            fos.close();
            socket.close();
        }*/

    }
}
