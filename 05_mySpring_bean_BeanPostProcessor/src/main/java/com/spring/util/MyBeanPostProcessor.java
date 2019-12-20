package com.spring.util;


import com.spring.service.impl.SomeService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自定义bean后处理器
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        //返回bean或者bean对象监控代理对象
        System.out.println("bean对象初始化之前。。。。");
        return bean;
    }

    public Object postProcessAfterInitialization(final Object beanInstance, String beanName) throws Exception {

        //为当前bean对象注册一个代理监控对象，负责去增强当前bean对象方法的能力
        Class beanInstanceClass = beanInstance.getClass();
        if(beanInstanceClass == SomeService.class){
            Object proxy = Proxy.newProxyInstance(beanInstance.getClass().getClassLoader(),
                    beanInstance.getClass().getInterfaces(),
                    new InvocationHandler() {
                        /**
                         *
                         * @param proxy: 代理监控对象
                         * @param method:doSome，监控的行为
                         * @param args：监控的行为方法的实参
                         * @return
                         * @throws Throwable
                         */
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("SomeService doSome方法被拦截");
                            String result = (String) method.invoke(beanInstance, args); //beanIntance.doSome();

                            return result.toUpperCase();
                        }
                    });
            return proxy;
        }

        return beanInstance;
    }


}
