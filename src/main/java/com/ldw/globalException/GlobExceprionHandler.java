package com.ldw.globalException;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/5/19.
 * 在applicationContext。xml中注册该全局异常处理类bean
 * 当全局出现异常且未处理时系统会在动调用该类中的resolveExceprion方法
 */
public class GlobExceprionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("error","系统遇到一个未知错误！错误信息如下：/n"+e.toString());
        System.out.println(e.toString());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
