package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

class ConstructorDependencyFinder extends AbstractDependenciesFinder<Constructor<?>> {
    protected ConstructorDependencyFinder(
            DependencyInjectionDialect dialect) {
        super(dialect);
    }

    @Override
    protected Collection<Constructor<?>> findAllMembers(Class<?> clazz) {
        return asList(clazz.getDeclaredConstructors());
    }

    @Override
    protected Set<BeanInfo> getDependencies(Constructor<?> constructor) {
        List<Class<?>> parameterTypes = asList(constructor.getParameterTypes());
        List<Annotation[]> annotations = asList(constructor.getParameterAnnotations());
        return IntStream.range(0, parameterTypes.size())
                .mapToObj(index -> extractBeanData(parameterTypes, annotations, index))
                .collect(toSet());
    }

    @Override
    protected DependencyType dependencyType() {
        return DependencyType.CONSTRUCTOR;
    }

    private BeanInfo extractBeanData(
            List<Class<?>> parameterTypes,
            List<Annotation[]> annotations,
            int index) {
        Class<?> dependency = parameterTypes.get(index);
        List<Annotation> paramAnnotations = asList(annotations.get(index));
        String beanName = extractBeanName(paramAnnotations)
                .orElse(dependency.getName());
        return new BeanInfo(dependency, beanName);
    }
}
