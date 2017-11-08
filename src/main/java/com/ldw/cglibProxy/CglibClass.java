package com.ldw.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.functions.T;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/23.
 */
public class CglibClass implements MethodInterceptor {
    private Object ob;

    public Object getInstance(Object o){
        this.ob=o;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(o.getClass());
        enhancer.setCallback(this);
        Object obj = enhancer.create();
        return obj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("使用cglib进行方法加强");
        return methodProxy.invoke(ob,objects);
    }
}
