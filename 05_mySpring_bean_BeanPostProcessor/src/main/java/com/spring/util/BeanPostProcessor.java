package com.spring.util;

import com.sun.istack.internal.Nullable;

/**
 * @author laochen
 */
public interface BeanPostProcessor {

    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }


}
