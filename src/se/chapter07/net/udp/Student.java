package se.chapter07.net.udp;

/**
 * @ClassName: Student
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/7 9:57
 * @Version 1.0
 **/
public class Student {
    public static void main(String[] args) {
        new Thread(new TalkSend(6666,"127.0.0.1",8888)).start();
        new Thread(new TalkRecive(7777)).start();
    }
}
