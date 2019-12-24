package com.spring.service.impl;

import com.spring.service.BaseService;

public class Person implements BaseService {
    //切入点PointCut，主要业务方法

    public void eat() {
        System.out.println("吃他妈的香蕉皮");
    }

    public void wc() {
        System.out.println("拉屎撒尿");
    }
}
