package com.dpancerz.ioc;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import static java.util.Arrays.asList;

class FieldDependencyFinder extends AbstractDependenciesFinder<Field> {
    private final FieldFinder fieldFinder;

    FieldDependencyFinder(MapReducer<String, Class<?>> accumulator,
                                    DependencyInjectionDialect dialect,
                                    FieldFinder fieldFinder) {
        super(accumulator, dialect);
        this.fieldFinder = fieldFinder;
    }

    @Override
    protected Collection<Field> findAllMembers(Class<?> clazz) {
        return fieldFinder.findAllFields(clazz);
    }

    @Override
    protected Map<String, Class<?>> getDependencies(Field member) {
        String beanName = extractBeanName(member.getType(),
                asList(member.getDeclaredAnnotations()))
                .orElse(member.getName());

        return Map.of(beanName, member.getType());
    }
}
