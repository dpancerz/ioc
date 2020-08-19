package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.List.of;
import static java.util.stream.Collectors.toSet;

class SetterDependencyFinder extends AbstractDependenciesFinder<Method> {
    private final MethodFinder methodFinder;
    protected SetterDependencyFinder(
            DependencyInjectionDialect dialect,
            MethodFinder methodFinder) {
        super(dialect);
        this.methodFinder = methodFinder;
    }

    @Override
    protected Collection<Method> findAllMembers(Class<?> clazz) {
        return methodFinder.findAllMethods(clazz);
    }

    @Override
    protected Set<BeanInfo> getDependencies(Method method) {
        return Optional.of(method)
                .filter(this::isSetter)
                .map(this::toBeanNames)
                .orElseGet(HashSet::new);
    }

    @Override
    protected DependencyType dependencyType() {
        return DependencyType.SETTER;
    }

    private boolean isSetter(Method method) { //TODO imperfect- return true on e.g. 'settings(sth)'
        return method.getName().startsWith("set")
                && method.getParameterCount() == 1;
    }

    private Set<BeanInfo> toBeanNames(Method method) {
        return stream(method.getDeclaredAnnotations())
                .filter(super::isDialectAnnotation)
                .map(annotation -> toBeanInfo(annotation, method))
                .collect(toSet());
    }

    private BeanInfo toBeanInfo(
            final Annotation annotation,
            final Method method) {
        return new BeanInfo(
                method.getDeclaringClass(),
                extractName(method, annotation));
    }

    private String extractName(Method method, Annotation ann) {
        return extractBeanName(of(ann))
                .orElseGet(() -> nameFromSetter(method));
    }

    private String nameFromSetter(Method method) {
        String withFirstCapital = method.getName().split("set")[0];
        String tail = withFirstCapital.substring(1);
        char lowercaseHead = withFirstCapital.toLowerCase().charAt(0);
        return lowercaseHead + tail;
    }
}
