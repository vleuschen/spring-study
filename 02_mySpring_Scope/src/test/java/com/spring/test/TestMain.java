package com.spring.test;

import com.spring.beans.Teacher;
import com.spring.util.BeanDefined;
import com.spring.util.BeanFactory;

import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //1、声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setBeanId("teacher");
        beanObj.setClassPath("com.spring.beans.Teacher");

        //2、在Spring核心配置文件中注册
        List<BeanDefined> beanDefinedList = new ArrayList<BeanDefined>();
        beanDefinedList.add(beanObj);

        //2、声明一个Spring提供的BeanFactory
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.setBeanDefinedList(beanDefinedList);

        //3、开发人员向BeanFactory中取出对象
        Teacher teacher = (Teacher) beanFactory.getBean("teacher");
        System.out.println(teacher);


    }

}
