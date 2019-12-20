package com.spring.util;

import com.spring.beans.Teacher;

public class TeacherFactory2 {


    public Teacher createTeacher(){
        Teacher teacher = new Teacher();
        //teacher.setTname("Mr");
        System.out.println("TeacherFactory 负责创建teacher实体对象。。");
        return teacher;
    }

}
