package se.chapter06.reflect.proxy;

/**
 * @ClassName: RealSubject
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/4 15:56
 * @Version 1.0
 **/
public class RealSubject implements Subject ,Subject2{

    @Override
    public void requesr() {
        System.out.println("我是真实对象！");
    }

    @Override
    public void requser2() {
        System.out.println("我是真实对象！__2");
    }

    @Override
    public void requesr3() {
        System.out.println("我是真实对象！__3");
    }
}
