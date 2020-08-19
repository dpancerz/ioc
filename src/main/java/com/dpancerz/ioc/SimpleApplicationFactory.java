package com.dpancerz.ioc;

import java.util.Collection;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

class SimpleApplicationFactory implements ApplicationFactory {
    private final DirectedManyRelationsGraphFactory graphFactory;
    private final DependencyInjectionDialect dialect;
    private final Collection<AbstractDependenciesFinder<?>> dependenciesFinders; //TODO aggregate to one and call like any other?

    SimpleApplicationFactory(
            DirectedManyRelationsGraphFactory graphFactory,
            DependencyInjectionDialect dialect,
            Collection<AbstractDependenciesFinder<?>> dependenciesFinders) {
        this.graphFactory = graphFactory;
        this.dialect = dialect;
        this.dependenciesFinders = dependenciesFinders;
    }

    @Override
    public Application createApplication(Set<Class<?>> scannedBeans) {
        Set<BeanInfo> applicationBeans = scannedBeans.stream()
                .map(this::toBeanInfo)
                .collect(toSet());
        Set<Dependency> allDependencies = applicationBeans.stream()
                .flatMap(beanInfo -> toDependencies(beanInfo).stream())
                .collect(toSet());
        DirectedManyRelationsGraph<BeanInfo, Dependency> dependenciesGraph =
                toGraph(applicationBeans, allDependencies);

        return null;
    }

    private DirectedManyRelationsGraph<BeanInfo, Dependency> toGraph(
            final Set<BeanInfo> scannedBeans,
            final Set<Dependency> allDependencies) {
        DirectedManyRelationsGraph<BeanInfo, Dependency> graph =
                graphFactory.graphWithoutEdges(scannedBeans, Dependency.class);
        allDependencies.forEach(dependency -> add(dependency, graph));
        return graph;
    }

    private void add(
            final Dependency dependency,
            final DirectedManyRelationsGraph<BeanInfo, Dependency> graph) {
        graph.addEdge(dependency.from(), dependency.to(), dependency);
    }

    private BeanInfo toBeanInfo(final Class<?> aClass) {
        return new BeanInfo(aClass,
                            new BeanNameExtractor(asList(aClass.getDeclaredAnnotations()), dialect).extractName()
                .orElse(aClass.getName()));
    }

    private Set<Dependency> toDependencies(BeanInfo beanInfo) {
        return dependenciesFinders.stream()
                .flatMap(finder -> finder.findDependencies(beanInfo)
                        .stream())
                .collect(toSet());
    }
}
