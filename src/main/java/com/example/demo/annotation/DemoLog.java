package com.example.demo.annotation;

import com.example.demo.constant.LogEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shichong on 2018/7/12.
 * 日志记录自定义注解
 */
@Target(ElementType.METHOD)     //注解使用的地方
@Retention(RetentionPolicy.RUNTIME) //元注解，生命周期，运行阶段，内在中的字节码，共三种
public @interface DemoLog { //注解类
    String value() default "";
    LogEnum module() default LogEnum.UNKNOW;
    LogEnum operate() default LogEnum.UNKNOW;


}
