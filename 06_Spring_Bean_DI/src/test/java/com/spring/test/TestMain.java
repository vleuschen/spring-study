package com.spring.test;

import com.spring.beans.Teacher;
import com.spring.service.BaseService;
import com.spring.service.impl.SomeService;
import com.spring.util.BeanDefined;
import com.spring.util.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("spring_config.xml");

        Teacher teacher = (Teacher) factory.getBean("teacher");
        System.out.println(teacher.getTeacherName());
        System.out.println(teacher.getFirends());
        System.out.println(teacher.getSchool());

    }

}
