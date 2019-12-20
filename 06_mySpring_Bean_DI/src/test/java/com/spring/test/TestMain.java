package com.spring.test;

import com.spring.beans.Teacher;
import com.spring.service.BaseService;
import com.spring.service.impl.SomeService;
import com.spring.util.BeanDefined;
import com.spring.util.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestMain {


    public static void main(String[] args) throws Exception {

        //1、声明注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setBeanId("teacher");
        beanObj.setClassPath("com.spring.beans.Teacher");

        /**
         * <property></property>
         */
        Map<String,String> propertyMap = beanObj.getPropertyMap();
        propertyMap.put("teacherName","李老师");
        propertyMap.put("friends","mike,老孙,小学妹");
        propertyMap.put("school","清华小学,北京大学,西安邮电大学");


        //声明工厂bean
        BeanDefined beanObj2 = new BeanDefined();

        //2、在Spring核心配置文件中注册
        List<BeanDefined> configuration = new ArrayList<BeanDefined>();
        configuration.add(beanObj);

        //3、声明一个Spring提供的BeanFactory
        BeanFactory beanFactory = new BeanFactory(configuration);
        beanFactory.setBeanDefinedList(configuration);

        //4、开发人员向BeanFactory中取出对象
        Teacher teacher = (Teacher) beanFactory.getBean("teacher");
        System.out.println("teacher="+teacher);
        System.out.println(teacher.getTeacherName());

    }

}
