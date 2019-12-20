package com.spring.beans;

import java.util.List;

/**
 * DI:依赖注入，Spring通过反射机制，调用属性的set方法进行赋值
 */
public class Teacher {

    private String teacherName;

    private String[] firends; //朋友数组

    private List<String> school; //老师呆过的学校数组

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String[] getFirends() {
        return firends;
    }

    public void setFirends(String[] firends) {
        this.firends = firends;
    }

    public List<String> getSchool() {
        return school;
    }

    public void setSchool(List<String> school) {
        this.school = school;
    }
}
