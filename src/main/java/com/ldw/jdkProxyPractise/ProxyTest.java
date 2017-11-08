package com.ldw.jdkProxyPractise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/5/19.
 */
public class ProxyTest {
    public static void main(String[] args) {
        IBaseImp iBaseImp = new IBaseImp();
        InvocationHandler proxyTest =new ProxyClass(iBaseImp);
        BaseInterface baseInterface =(BaseInterface) Proxy.newProxyInstance(iBaseImp.getClass().getClassLoader(), iBaseImp.getClass().getInterfaces(), proxyTest);
        baseInterface.practise();
    }
}
