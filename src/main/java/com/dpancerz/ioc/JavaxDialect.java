package com.dpancerz.ioc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

class JavaxDialect implements DependencyInjectionDialect {
    @Override
    public Set<Class<?>> beanAnnotations() {
        return newHashSet(Named.class, Qualifier.class);
    }

    @Override
    public Set<Class<?>> injectAnnotations() {
        return newHashSet(Inject.class);
    }

    @Override
    public String nameParameter() {
        return "value";
    }
}
