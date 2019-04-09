package com.dpancerz.ioc;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

class ReflectionsAnnotationProcessor implements AnnotationProcessor {
    @SuppressWarnings("unchecked")
    public Set<Class<?>> scanFor(String path, Collection<Class<?>> annotationClasses) {
        Reflections reflections = new Reflections(path);
        return annotationClasses.stream()
                .flatMap(cls -> reflections.getTypesAnnotatedWith((Class<? extends Annotation>) cls).stream())
                .collect(toSet());
    }
}
