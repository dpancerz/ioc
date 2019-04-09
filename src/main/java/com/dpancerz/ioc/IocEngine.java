package com.dpancerz.ioc;

import java.util.Set;

class IocEngine {
    private final String rootPackage;
    private final AnnotationProcessor annotationProcessor;
    private final ApplicationFactory applicationFactory;
    private final DependencyInjectionDialect dialect;

    IocEngine(String rootPackage,
              AnnotationProcessor annotationProcessor,
              ApplicationFactory applicationFactory, DependencyInjectionDialect dialect) {
        this.rootPackage = rootPackage;
        this.annotationProcessor = annotationProcessor;
        this.applicationFactory = applicationFactory;
        this.dialect = dialect;
    }

    public Application buildTheApplication() {
        Set<Class<?>> beanClasses = annotationProcessor
                .scanFor(rootPackage, dialect.beanAnnotations());
        return applicationFactory.createApplication(beanClasses);
    }
}
