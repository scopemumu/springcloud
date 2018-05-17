package com.estone.erp.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 * Spring工具类
 * 
 * @author Kevin
 *
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public static <E> E getBean(Class<E> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <E> E getBean(String beanName, Class<E> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }
}
