package se.chapter07.net.udp;

/**
 * @ClassName: Teacher
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/7 9:57
 * @Version 1.0
 **/
public class Teacher {
    //两边都输入拜拜才能结束，因为这是两个线程
    public static void main(String[] args) {
        new Thread(new TalkSend(9999,"127.0.0.1",7777)).start();
        new Thread(new TalkRecive(8888)).start();

    }
}
