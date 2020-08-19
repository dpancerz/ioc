package com.dpancerz.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

class BeanNameExtractor {
    private final List<Annotation> paramAnnotations;
    private final DependencyInjectionDialect dialect;

    public BeanNameExtractor(
            final List<Annotation> paramAnnotations,
            final DependencyInjectionDialect dialect) {
        this.paramAnnotations = paramAnnotations;
        this.dialect = dialect;
    }

    public Optional<String> extractName() {
        return paramAnnotations.stream()
                .filter(annotation -> new DialectAnnotationPredicate(
                        annotation,
                        dialect).isDialectAnnotation())
                .findAny()
                .map(this::extractBeanName);
    }

    private String extractBeanName(Annotation annotation) {
        try {
            Field field = annotation.getClass().getField(dialect.nameParameter());
            return field.get(annotation).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
