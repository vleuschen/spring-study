package com.spring.test;

import com.spring.beans.Teacher;
import com.spring.service.BaseService;
import com.spring.service.impl.Person;
import com.spring.service.impl.SomeService;
import com.spring.util.BeanDefined;
import com.spring.util.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("spring_config.xml");

        BaseService personProxy = (BaseService) factory.getBean("personProxy");
        personProxy.eat();
        personProxy.wc();
    }

}
