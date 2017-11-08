package com.ldw.cglibProxy;

/**
 * Created by Administrator on 2017/5/23.
 */
public class CglibTest {
    public static void main(String[] args) {
        CglibClass cglibClass=new CglibClass();
        BaseClass instance = (BaseClass) cglibClass.getInstance( new BaseClass());
        instance.add();
    }
}
