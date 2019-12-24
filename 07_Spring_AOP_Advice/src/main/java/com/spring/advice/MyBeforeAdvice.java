package com.spring.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

//定义切面
public class MyBeforeAdvice implements MethodBeforeAdvice {

    //切面：次要业务

    /**
     *
     * @param method
     * @param objects
     * @param o
     * @throws Throwable
     */
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("***洗手***");
    }
}
