package com.dpancerz.ioc;

import java.lang.annotation.Annotation;

class DialectAnnotationPredicate {
    private final Annotation annotation;
    private final DependencyInjectionDialect dialect;

    public DialectAnnotationPredicate(
            final Annotation annotation,
            final DependencyInjectionDialect dialect) {
        this.annotation = annotation;
        this.dialect = dialect;
    }

    public boolean isDialectAnnotation() {
        return dialect
                .injectAnnotations()
                .contains(annotation.getClass());
    }
}
