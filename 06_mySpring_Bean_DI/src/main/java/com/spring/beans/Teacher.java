package com.spring.beans;

import java.util.List;

public class Teacher {

    private String teacherName;

    private String[] friends;

    private List school;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(Object friends) {
        this.friends = (String[]) friends;
    }

    public List getSchool() {
        return school;
    }

    public void setSchool(List school) {
        this.school = school;
    }
}
