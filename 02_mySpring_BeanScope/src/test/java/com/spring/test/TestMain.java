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
     //   beanObj.setScope("prototype"); //设置原型对象

        //2、在Spring核心配置文件中注册
        List<BeanDefined> beanDefinedList = new ArrayList<BeanDefined>();
        beanDefinedList.add(beanObj);

        //3、声明一个Spring提供的BeanFactory
        BeanFactory beanFactory = new BeanFactory(beanDefinedList);
        beanFactory.setBeanDefinedList(beanDefinedList);

        //4、开发人员向BeanFactory中取出对象
        Teacher teacher1 = (Teacher) beanFactory.getBean("teacher");
        Teacher teacher2 = (Teacher) beanFactory.getBean("teacher");
        System.out.println("t1="+teacher1);
        System.out.println("t2="+teacher2);


    }

}
