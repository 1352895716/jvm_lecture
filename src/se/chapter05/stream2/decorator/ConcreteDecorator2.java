package se.chapter05.stream2.decorator;

import org.junit.Test;

/**
 * @ClassName: concreteDecorator2
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/27 11:57
 * @Version 1.0
 **/
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(){
        super();
    }
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void dosomething() {
        super.dosomething();
        another();
    }
    public void another(){
        System.out.println("功能C");
    }

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Decorator decorator = new Decorator(component);
        Decorator decorator1 = new ConcreteDecorator(decorator);
        Decorator decorator2 = new ConcreteDecorator2(decorator1);
        decorator2.dosomething();
    }
}
