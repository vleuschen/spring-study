package com.spring.util;

import java.util.List;

public class BeanFactory {

    private List<BeanDefined> beanDefinedList;

    public List<BeanDefined> getBeanDefinedList() {
        return beanDefinedList;
    }

    public void setBeanDefinedList(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
    }

    public Object getBean(String beanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object instance;
        for (BeanDefined beanObj : beanDefinedList) {
            if(beanId.equals(beanObj.getBeanId())){
                String classPath = beanObj.getClassPath();
                Class classFile = Class.forName(classPath);
                //在默认i情况下，Spring工厂是通过当前类的默认构造方法创建当前类的实例对象
                instance = classFile.newInstance();
                return instance;
            }
        }
        return null;
    }

}
