package se.chapter05.stream2.pipedstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @ClassName: PipedTest01
 * 管道流
 * 输入输出可以直接连接，所以常配合多线程使用
 * 两种连接方式
 * 1.初始化流的时候连接
 * 2.调用方法连接
 **/
public class PipedTest01 {

    public static void main(String[] args) throws IOException {
        PipedInputStream input = new PipedInputStream();
        PipedOutputStream output = new PipedOutputStream();

        input.connect(output);

        new Thread(new Input(input)).start();
        new Thread(new Output(output)).start();
    }
}
class Input implements Runnable{
    PipedInputStream in = null;
    public  Input(PipedInputStream in){
        this.in = in;
    }
    public void run(){
        try {
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String str = new String(buf,0,len);
            System.out.println("S="+str);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Output implements Runnable{
    PipedOutputStream out = null;
    public Output(PipedOutputStream out){
        this.out = out;
    }
    public void run(){
        try {
            Thread.sleep(5000);
           out.write("hi 挂袋来了！".getBytes());
           out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
