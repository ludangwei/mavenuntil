package com.ldw.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/16.
 * 在web.xml中同时加载aop.xml和springMvc.XML（不是在applicationContext.XML中加载）；
 * 使用注解形式的aop，在类明上增加@aspect注解
 */
@Component
@Aspect
public class AopClass {
    @Pointcut("@annotation(com.ldw.util.CustomAnnotation)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void aopMethod() {
        DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("这是spring的aop前置输出，当前时间为" + dt.format(new Date())+this.getClass().getName());
    }

    @After("pointCut()")
    public void aopMethod1() {
        DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("这是spring的后置输出，当前时间为" + dt.format(new Date())+this.getClass().getName());
    }
}
