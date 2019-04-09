package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

class ConstructorDependencyFinder extends AbstractDependenciesFinder<Constructor<?>> {
    protected ConstructorDependencyFinder(MapReducer<String, Class<?>> accumulator,
                                          DependencyInjectionDialect dialect) {
        super(accumulator, dialect);
    }

    @Override
    protected Collection<Constructor<?>> findAllMembers(Class<?> clazz) {
        return asList(clazz.getDeclaredConstructors());
    }

    @Override
    protected Map<String, Class<?>> getDependencies(Constructor<?> constructor) {
        List<Class<?>> parameterTypes = asList(constructor.getParameterTypes());
        List<Annotation[]> annotations = asList(constructor.getParameterAnnotations());
        return IntStream.range(0, parameterTypes.size())
                .mapToObj(index -> extractBeanData(parameterTypes, annotations, index))
                .collect(toMap(Pair::getLeft, Pair::getRight));
    }

    private Pair<String, ? extends Class<?>> extractBeanData(List<Class<?>> parameterTypes,
                                                             List<Annotation[]> annotations,
                                                             int index) {
        Class<?> dependency = parameterTypes.get(index);
        List<Annotation> paramAnnotations = asList(annotations.get(index));
        String beanName = extractBeanName(dependency, paramAnnotations)
                .orElse(dependency.getName());
        return Pair.of(beanName, dependency);
    }
}
