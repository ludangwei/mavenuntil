package com.ldw.jdkProxyPractise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/19.
 */
public class ProxyClass implements InvocationHandler {
    private Object object;

    public ProxyClass(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("this is a method of proxyClass ");
        method.invoke(object,args);
        return null;
    }
}
