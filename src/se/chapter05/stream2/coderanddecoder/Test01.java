package se.chapter05.stream2.coderanddecoder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: Test01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/4 9:16
 * @Version 1.0
 **/
public class Test01 {
    @Test
    public void test01() throws UnsupportedEncodingException {
        /*
         * @Autor: 13528
         * 本机默认的编码格式为utf-8
         * 你好
         * gbk：  -60  -29  -70  -61
         * utf-8: -28  -67  -96  -27  -91  -67
         */
        //本机默认的编码格式为utf-8,所以中文编解码都是使用utf-8
        byte[] buf  = "你好".getBytes();
        String str = new String(buf);
        System.out.println(str);
    }

    //编错了，解不出来
    //编对了，解错了，有可能有救（这是因为解码时，所用解码表中，并不一定有你编码出来的字节，
    // 如果有，还能还原，否则就使用默认字符，导致码变了而无法还原）
    @Test
    public void test02() throws UnsupportedEncodingException {
        byte[] buf = "你好".getBytes("gbk");
        String str = new String(buf,"iso8859-1");
        System.out.println(str);
        byte[] buf2 = str.getBytes("iso8859-1");
        String str2 = new String(buf,"gbk");
        System.out.println(str2);

    }

    //按字节截取字符串(使用gbk编码)
    @Test
    public void test03() throws UnsupportedEncodingException {
        String str = "s你好dfs饭卡";
        //byte[] buf = str.getBytes("gbk");
        byte[] buf = str.getBytes("utf-8");
        for(int i=0;i<buf.length;i++){
            //System.out.println("截取"+(i+1)+"个字节："+sub_gbk(buf,i+1));
            System.out.println("截取"+(i+1)+"个字节："+sub_utf8(buf,i+1));
        }
    }

    private String sub_utf8(byte[] buf, int i) {
        int count = 0;
        for (int x=i-1;x>=0;x--){
            if (buf[x]<0)
                count++;
            else break;
        }
        if(count%3==0)
            return new String(buf,0,i);
        else if(count%3==1)
            return new String(buf,0,i-1);
        else  return new String(buf,0,i-2);
    }

    private String sub_gbk(byte[] buf,int i) throws UnsupportedEncodingException {
        int count = 0;
        for (int x=i-1;x>=0;x--){
            if (buf[x]<0)
                count++;
            else break;
        }
        if(count%2==0)
            return new String(buf,0,i,"gbk");
        else return new String(buf,0,i-1,"gbk");
    }
}
