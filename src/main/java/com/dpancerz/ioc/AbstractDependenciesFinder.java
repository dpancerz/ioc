package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.*;

abstract class AbstractDependenciesFinder<E extends Member> {
    private final MapReducer<String, Class<?>> accumulator;
    private final DependencyInjectionDialect dialect;

    protected AbstractDependenciesFinder(MapReducer<String, Class<?>> accumulator,
                                         DependencyInjectionDialect dialect) {
        this.accumulator = accumulator;
        this.dialect = dialect;
    }

    public Map<String, Class<?>> findDependencies(Class<?> clazz) {
        return findAllMembers(clazz)
                .stream()
                .map(this::getDependencies)
                .reduce(accumulator)
                .orElseGet(HashMap::new);
    }

    protected abstract Map<String, Class<?>> getDependencies(E member);

    protected abstract Collection<E> findAllMembers(Class<?> clazz);

    protected DependencyInjectionDialect dialect() {
        return dialect;
    }

    protected Optional<String> extractBeanName(Class<?> dependency, List<Annotation> paramAnnotations) {
        return paramAnnotations.stream()
                .filter(this::isDialectAnnotation)
                .findAny()
                .map(this::extractBeanName);
    }

    protected boolean isDialectAnnotation(Annotation annotation) {
        return dialect()
                .injectAnnotations()
                .contains(annotation.getClass());
    }

    private String extractBeanName(Annotation annotation) {
        try {
            Field field = annotation.getClass().getField(dialect().nameParameter());
            return field.get(annotation).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
