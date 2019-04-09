package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.List.of;
import static java.util.stream.Collectors.toMap;

class SetterDependencyFinder extends AbstractDependenciesFinder<Method> {
    private final MethodFinder methodFinder;
    protected SetterDependencyFinder(MapReducer<String, Class<?>> accumulator,
                                     DependencyInjectionDialect dialect,
                                     MethodFinder methodFinder) {
        super(accumulator, dialect);
        this.methodFinder = methodFinder;
    }

    @Override
    protected Collection<Method> findAllMembers(Class<?> clazz) {
        return methodFinder.findAllMethods(clazz);
    }

    @Override
    protected Map<String, Class<?>> getDependencies(Method method) {
        return Optional.of(method)
                .filter(this::isSetter)
                .map(this::toBeanNames)
                .orElseGet(HashMap::new);
    }

    private boolean isSetter(Method method) { //TODO imperfect- return true on e.g. 'settings(sth)'
        return method.getName().startsWith("set")
                && method.getParameterCount() == 1;
    }

    private Map<String, Class<?>> toBeanNames(Method method) {
        return stream(method.getDeclaredAnnotations())
                .filter(super::isDialectAnnotation)
                .collect(toMap(ann -> extractName(method, ann),
                        ann -> method.getDeclaringClass()));
    }

    private String extractName(Method method, Annotation ann) {
        return extractBeanName(method.getDeclaringClass(), of(ann))
                .orElseGet(() -> nameFromSetter(method));
    }

    private String nameFromSetter(Method method) {
        String withFirstCapital = method.getName().split("set")[0];
        String tail = withFirstCapital.substring(1);
        char lowercaseHead = withFirstCapital.toLowerCase().charAt(0);
        return lowercaseHead + tail;
    }
}
