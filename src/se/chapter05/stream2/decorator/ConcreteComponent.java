package se.chapter05.stream2.decorator;

/**
 * @ClassName: ConcreteComponent
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/27 11:51
 * @Version 1.0
 **/
public class ConcreteComponent implements Component {

    @Override
    public void dosomething() {
        System.out.println("功能A");
    }
}
