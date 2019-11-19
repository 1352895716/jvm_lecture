package com.zy;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @ClassName: Test06
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/10/30 17:07
 * @Version 1.0
 **/
public class Test06 {

    public static void main(String[] args) throws Exception {
      ClassLoader classloader = ClassLoader.getSystemClassLoader();
      System.out.println(classloader);
      while (null != classloader) {
          classloader = classloader.getParent();
          System.out.println(classloader);
      }
      System.out.println("---------------------");
      show();
    }
    public static void show() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String resourceName = "com/zy/Test06.class";
        Enumeration<URL> urls = loader.getResources(resourceName);
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
        }
    }

}
