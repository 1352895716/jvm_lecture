package se.chapter01.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: MapTest
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/2 17:41
 * @Version 1.0
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("a","aaa");
        map.put("b","bbb");
        map.put("c","ccc");

        Set<Map.Entry<String,String>> set = map.entrySet();
        for(Iterator iterator=set.iterator();iterator.hasNext();){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:"+key+" value:"+value);
        }
    }
}

class Person{
    int id;
    String name;
    int age;
}


