package com.spring.test;

import com.spring.beans.Teacher;
import com.spring.service.BaseService;
import com.spring.service.impl.SomeService;
import com.spring.util.BeanDefined;
import com.spring.util.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args) throws Exception {

        //1、声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setBeanId("someService");
        beanObj.setClassPath("com.spring.service.impl.SomeService");

        //声明工厂bean
        BeanDefined beanObj2 = new BeanDefined();
        beanObj2.setClassPath("com.spring.util.MyBeanPostProcessor");

        //2、在Spring核心配置文件中注册
        List<BeanDefined> configuration = new ArrayList<BeanDefined>();
        configuration.add(beanObj);
        configuration.add(beanObj2);

        //3、声明一个Spring提供的BeanFactory
        BeanFactory beanFactory = new BeanFactory(configuration);
        beanFactory.setBeanDefinedList(configuration);

        //4、开发人员向BeanFactory中取出对象
        BaseService someService = (BaseService) beanFactory.getBean("someService");
        System.out.println("baseService"+someService);
        System.out.println(someService.doSome());

    }

}
