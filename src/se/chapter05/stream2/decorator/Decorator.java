package se.chapter05.stream2.decorator;

/**
 * @ClassName: Decorator
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/27 11:53
 * @Version 1.0
 **/
public class Decorator implements Component{

    private Component component;
    public Decorator(){}
    public  Decorator(Component component){
        this.component = component;
    }
    @Override
    public void dosomething() {
        component.dosomething();
    }
}
