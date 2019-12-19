package com.spring.beans;

public class TeacherFactory {


    public Teacher createTeacher(){
        Teacher teacher = new Teacher();
        System.out.println("TeacherFactory 负责创建teacher实体对象。。");
        return teacher;
    }

}
