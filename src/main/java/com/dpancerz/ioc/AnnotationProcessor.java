package com.dpancerz.ioc;

import java.util.Collection;
import java.util.Set;

interface AnnotationProcessor {
    Set<Class<?>> scanFor(String path, Collection<Class<?>> annotationClasses);
}
