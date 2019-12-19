package com.spring.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {

    private List<BeanDefined> beanDefinedList;

    private Map<String,Object> springIOC; //存放已经初始化好的实例对象

    public BeanFactory(List<BeanDefined> beanDefinedList) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        this.beanDefinedList = beanDefinedList;
        //放置所有scope为singleton的对象。采用单例的对象
        springIOC = new HashMap<String, Object>();
        for (BeanDefined beanObj : beanDefinedList) {
            if("singleton".equals(beanObj.getScope())){
                Class classFile = Class.forName(beanObj.getClassPath());
                Object instance = classFile.newInstance();
                springIOC.put(beanObj.getBeanId(),instance);
            }
        }

    }

    public List<BeanDefined> getBeanDefinedList() {
        return beanDefinedList;
    }

    public void setBeanDefinedList(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
    }

    public Object getBean(String beanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object instance = null;
        for (BeanDefined beanObj : beanDefinedList) {
            if(beanId.equals(beanObj.getBeanId())){
                String classPath = beanObj.getClassPath();
                Class classFile = Class.forName(classPath);
                //得到Scope的值
                String scope = beanObj.getScope();
                if("prototype".equals(scope)){ //要求每次调用时都要返回一个全新的实例对象
                    instance = classFile.newInstance();
                }else{
                    //拿出单例对象
                    instance = springIOC.get(beanObj.getBeanId());
                }

                //在默认i情况下，Spring工厂是通过当前类的默认构造方法创建当前类的实例对象
                instance = classFile.newInstance();
                return instance;
            }
        }
        return null;
    }

}
