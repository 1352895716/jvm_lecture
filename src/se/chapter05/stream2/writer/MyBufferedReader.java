package se.chapter05.stream2.writer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @ClassName: MyBufferedReader
 * @Description:  自定义一个缓冲流
 * @Autor:13528
 * @Date: 2019/11/29 15:59
 * @Version 1.0
 **/
public class MyBufferedReader {

    private Reader fr = null;
    private int pos = 0;
    private int count = 0;
    private char[] buf = new char[1024];

    public MyBufferedReader(Reader fr){
        this.fr = fr;
    }

    public int myRead() throws IOException {
        if(count == 0){
            count = fr.read(buf);
            pos = 0;
        }
        if(count < 0){
            return -1;
        }
        char ch = buf[pos++];
        count--;
        return ch;
    }
    public String myReadLine() throws IOException {

        StringBuilder sb = new StringBuilder();
        int ch = 0;
        while (-1 != (ch = myRead())){
            if(ch == '\r')
                continue;
            if(ch == '\n')
                return sb.toString();
            sb.append((char)ch);
        }
        if(sb.length()>0)
            return sb.toString();
        return null;
    }
    public void myClose() throws IOException {

        if(fr != null)
            fr.close();
    }

}
