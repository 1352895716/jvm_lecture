package se.chapter05.stream2.decorator;

/**
 * @ClassName: ConcreteDecorator
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/27 11:55
 * @Version 1.0
 **/
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void dosomething() {
        super.dosomething();
        another();
    }
    public void another(){
        System.out.println("功能B");
    }
}
