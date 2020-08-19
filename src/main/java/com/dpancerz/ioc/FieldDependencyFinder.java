package com.dpancerz.ioc;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

import static java.util.Arrays.asList;

class FieldDependencyFinder extends AbstractDependenciesFinder<Field> {
    private final FieldFinder fieldFinder;

    FieldDependencyFinder(
            DependencyInjectionDialect dialect,
            FieldFinder fieldFinder) {
        super(dialect);
        this.fieldFinder = fieldFinder;
    }

    @Override
    protected Collection<Field> findAllMembers(Class<?> clazz) {
        return fieldFinder.findAllFields(clazz);
    }

    @Override
    protected Set<BeanInfo> getDependencies(Field member) {
        String beanName = extractBeanName(asList(member.getDeclaredAnnotations()))
                .orElse(member.getName());

        return Set.of(new BeanInfo(member.getType(), beanName));
    }

    @Override
    protected DependencyType dependencyType() {
        return DependencyType.FIELD;
    }
}
