package com.dpancerz.ioc;

import static java.lang.String.format;

class Bean {
    private final BeanInfo info;
    private final Object bean;

    Bean(
            final BeanInfo info,
            final Object bean) {
        if (isWrongClass(info, bean)) {
            throw wrongBeanClass(info, bean);
        }
        this.info = info;
        this.bean = bean;
    }

    private boolean isWrongClass(
            final BeanInfo info,
            final Object bean) {
        return !info.getClazz().isAssignableFrom(bean.getClass());
    }

    private RuntimeException wrongBeanClass(
            final BeanInfo info,
            final Object bean) {
        return new IllegalArgumentException(
                format("Bean %s cannot be passed as '%s'",
                       bean, info.getName()));
    }
}
