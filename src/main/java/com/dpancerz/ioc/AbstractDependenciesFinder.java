package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

abstract class AbstractDependenciesFinder<E extends Member> {
    private final DependencyInjectionDialect dialect;

    protected AbstractDependenciesFinder(
            DependencyInjectionDialect dialect) {
        this.dialect = dialect;
    }

    public Set<Dependency> findDependencies(BeanInfo parent) {
        return findAllMembers(parent.getClazz()).stream()
                .flatMap(member -> getDependencies(member).stream())
                .map((BeanInfo bean) -> toDependency(parent, bean))
                .collect(toSet());
    }

    protected abstract Set<BeanInfo> getDependencies(E member);

    protected abstract DependencyType dependencyType();

    protected abstract Collection<E> findAllMembers(Class<?> clazz);

    protected Optional<String> extractBeanName(
            List<Annotation> paramAnnotations) {
        return new BeanNameExtractor(paramAnnotations, dialect).extractName();
    }

    protected boolean isDialectAnnotation(Annotation annotation) {
        return new DialectAnnotationPredicate(annotation, dialect).isDialectAnnotation();
    }

    private Dependency toDependency(
            final BeanInfo parent,
            final BeanInfo dependency) {
        return new Dependency(parent, dependency, dependencyType());
    }
}
