package com.spring.util;

import java.util.HashMap;
import java.util.Map;

public class BeanDefined {
    /**
     * 该类就对应spring容器xml里的标签
     * <
     *  bean id class scope factory-bean factory-method
     * >
     */
    private String beanId;
    private String classPath;
    private String scope = "singleton";
    private String factoryBean = null;
    private String factoryMethod = null;
    private Map<String,String> propertyMap = new HashMap<>();

    public String getFactoryBean() {
        return factoryBean;
    }

    public void setFactoryBean(String factoryBean) {
        this.factoryBean = factoryBean;
    }

    public String getFactoryMethod() {
        return factoryMethod;
    }

    public void setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
