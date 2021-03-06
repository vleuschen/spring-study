package com.spring.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BeanFactory {

    private List<BeanDefined> beanDefinedList;

    private Map<String,Object> springIOC; //存放已经初始化好的实例对象

    private BeanPostProcessor processorObj; //后置对象

    //实现Spring框架的依赖注入
    public void setValue(Object instance,Class classFile,Map propertyMap) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {

        Method[] declaredMethods = classFile.getDeclaredMethods();
        //循环遍历propertyMap<属性名，属性值>

        //获取属性名的集合
        Set fieldsNameSet = propertyMap.keySet();
        Iterator fieldIterator = fieldsNameSet.iterator();
        while (fieldIterator.hasNext()){
            String fieldName = (String) fieldIterator.next();
            String value = (String) propertyMap.get(fieldName);
            //同名属性对象
            Field fieldObj = classFile.getDeclaredField(fieldName);
            for (int i = 0; i < declaredMethods.length; i++) {
                Method methodObj = declaredMethods[i];
                String methodName = "set" + fieldName; //setid
                if(methodName.equalsIgnoreCase(methodObj.getName())){
                    Class fieldObjType = fieldObj.getType();//当前属性的数据类型 Integer,String,Double
                    if(fieldObjType == String.class){
                        methodObj.invoke(instance,value);
                    }else if(fieldObjType == Integer.class){
                        methodObj.invoke(instance,Integer.valueOf(value));
                    }else if(fieldObjType == Boolean.class){
                        methodObj.invoke(instance,Boolean.valueOf(value));
                    }else if(fieldObjType == List.class){
                        List tempList = new ArrayList();
                        String[] dataArray = value.split(",");
                        for (int j = 0; j < dataArray.length; j++) {
                            tempList.add(dataArray[j]);
                        }
                        methodObj.invoke(instance,tempList);
                    }else{ //认为其他类型是数组
                        String[] dataArray = value.split(",");
                        methodObj.invoke(instance,dataArray);
                    }
                }
            }
        }
    }

    public BeanFactory(List<BeanDefined> beanDefinedList) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        this.beanDefinedList = beanDefinedList;
        //放置所有scope为singleton的对象。采用单例的对象
        springIOC = new HashMap<String, Object>();
        for (BeanDefined beanObj : beanDefinedList) {
            if("singleton".equals(beanObj.getScope())){
                Class classFile = Class.forName(beanObj.getClassPath());
                Object instance = classFile.newInstance();
                //p判断当前对象是一个bean对象还是一个后置处理对象
                isProcessor(instance,classFile);
                springIOC.put(beanObj.getBeanId(),instance);
            }
        }

    }

    private void isProcessor(Object instance,Class classFile){

        Class[] interfaceArray = classFile.getInterfaces();

        if(interfaceArray == null){
            return;
        }

        for (int i = 0; i < interfaceArray.length; i++) {
            Class interfaceType =  interfaceArray[i];
            if(interfaceType == BeanPostProcessor.class){
                this.processorObj = (BeanPostProcessor) instance;
            }
        }

    }


    public List<BeanDefined> getBeanDefinedList() {
        return beanDefinedList;
    }

    public void setBeanDefinedList(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
    }

    public Object getBean(String beanId) throws Exception {
        Object instance = null;
        Object proxyObj = null; //当前实例对象的代理监控对象
        for (BeanDefined beanObj : beanDefinedList) {
            if(beanId.equals(beanObj.getBeanId())){
                String classPath = beanObj.getClassPath();
                Class classFile = Class.forName(classPath);
                //得到Scope的值
                String scope = beanObj.getScope();
                String factoryBean = beanObj.getFactoryBean();
                String factoryMethod = beanObj.getFactoryMethod();
                Map propertyMap = beanObj.getPropertyMap();
                if("prototype".equals(scope)){ //要求每次调用时都要返回一个全新的实例对象
                    //为true说明用户想使用自定义工厂创建实例对象
                    if(factoryBean != null && factoryMethod != null){
                        Object factoryObj = springIOC.get(factoryBean);
                        Class<?> factoryClass = factoryObj.getClass();
                        Method methodObj = factoryClass.getDeclaredMethod(factoryMethod,null);
                        methodObj.setAccessible(true);
                        instance = methodObj.invoke(factoryObj);
                    }else{
                        instance = classFile.newInstance();
                    }

                }else{
                    //拿出单例对象
                    instance = springIOC.get(beanObj.getBeanId());
                }

                if(this.processorObj != null){
                    proxyObj = this.processorObj.postProcessBeforeInitialization(instance,beanId);
                    //实例对象初始化，Spring依赖注入
                    setValue(instance,classFile,propertyMap);
                    proxyObj = this.processorObj.postProcessAfterInitialization(instance,beanId);
                    //此时返回proxyObj可那是原始bean对象，也可能是代理对象

                    return proxyObj;
                }else{

                    //实例对象的初始化处理
                    setValue(instance,classFile,propertyMap);

                    return instance;
                }



            }
        }
        return null;
    }

}
