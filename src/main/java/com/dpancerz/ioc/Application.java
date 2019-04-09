package com.dpancerz.ioc;

interface Application {
    /**
     *
     * @param beanClass class of bean to be returned, preferably interface class
     * @return
     */
    <T> T getBean(Class<T> beanClass);

    /**
     *
     * @param name name of the bean to be returned.
     * @return
     */
    <T> T getBean(String name);
}
