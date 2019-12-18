package com.spring.beans;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("spring_config.xml");
        Student student = (Student) factory.getBean("student");

        System.out.println(student.getSname()+" "+student.getAge());
        System.out.println(student.getTeacher().getTname());

    }



}
