package com.dpancerz.ioc;

import java.util.Set;

interface DependencyInjectionDialect {
    /**
     * e.g. , Springs's @Component
     */
    Set<Class<?>> beanAnnotations();

    /**
     * e.g. javax.@Inject, Springs's @Autowired
     */
    Set<Class<?>> injectAnnotations();

    /**
     * Name of the parameter on annotation, that defines the bean,
     * e.g. in case @Named(value = "atmService") => nameParameter = value.
     */
    String nameParameter();
}
