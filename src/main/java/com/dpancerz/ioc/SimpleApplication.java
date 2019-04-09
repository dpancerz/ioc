package com.dpancerz.ioc;

import java.util.Map;

class SimpleApplication implements Application {
    private final Map<String, Object> allBeans;
    private final Map<Class<?>, Object> classBeansView;

    SimpleApplication(Map<String, Object> allBeans) {
        this.allBeans = allBeans;
        this.classBeansView = toClassView(allBeans);
    }

    private Map<Class<?>, Object> toClassView(Map<String, Object> allBeans) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return null;
    }

    @Override
    public <T> T getBean(String name) {
        return null;
    }
}
