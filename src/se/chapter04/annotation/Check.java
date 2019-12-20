package se.chapter04.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//意味注解有效期到程序运行
@Target(ElementType.METHOD)//该注解标注方法
public @interface Check {
}
