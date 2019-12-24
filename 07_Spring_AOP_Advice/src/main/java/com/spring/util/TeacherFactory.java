package com.spring.util;

import com.spring.beans.Teacher;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TeacherFactory {

    //Bean后处理器
    BeanPostProcessor beanPostProcessor;


    //使用静态工厂节省内存消耗
    public static Teacher createTeacher(){
        Teacher teacher = new Teacher();
        System.out.println("TeacherFactory 负责创建teacher实体对象。。");
        return teacher;
    }

}
