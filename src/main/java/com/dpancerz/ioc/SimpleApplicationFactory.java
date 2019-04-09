package com.dpancerz.ioc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class SimpleApplicationFactory implements ApplicationFactory {
    private final DirectedManyRelationsGraphFactory graphFactory;
    private final DependencyInjectionDialect dialect;
    private final Collection<AbstractDependenciesFinder<?>> dependenciesFinders; //TODO aggregate to one and call like any other?
    private final MapReducer<String, Class<?>> accumulator;

    SimpleApplicationFactory(DirectedManyRelationsGraphFactory graphFactory,
                             DependencyInjectionDialect dialect,
                             Collection<AbstractDependenciesFinder<?>> dependenciesFinders,
                             MapReducer<String, Class<?>> accumulator) {
        this.graphFactory = graphFactory;
        this.dialect = dialect;
        this.dependenciesFinders = dependenciesFinders;
        this.accumulator = accumulator;
    }

    @Override
    public Application createApplication(Set<Class<?>> scannedBeans) {
        scannedBeans.stream()
            .map(cls -> Pair.of(cls, dependenciesOf(cls)))
//                .map(this::findConstructor)
                ;
//        findDependencies();
//        buildDependenciesGraph();//check if cycles, if yes then don't allow both in constructor
//        createBeans();//consider using proxies
        Map<BeanInfo, Object> beans = new HashMap<>();
//        injectBeansByFieldOrSetters();
        return null;
    }

    private Map<String, Class<?>> dependenciesOf(Class<?> cls) {
        return dependenciesFinders.stream()
                .map(finder -> finder.findDependencies(cls))
                .reduce(accumulator)
                .orElseGet(HashMap::new);
//        Map<String, Class<?>> constructorDependencies = findAllConstructors(cls)
//                .stream()
//                .map(this::getDependenciesFromConstructor)
//                .reduce(new MapReducer<>())
//                .orElseGet(HashMap::new);
//        Map<String, Class<?>> fieldDependencies = findAllFields(cls)
//                .stream()
//                .map(this::getDependenciesFromFields)
//                .reduce(new MapReducer<>())
//                .orElseGet(HashMap::new);
//        Map<String, Class<?>> setterDependencies = findAllSetters(cls)
//                .stream()
//                .map(this::getDependenciesFromSetters)
//                .reduce(new MapReducer<>())
//                .orElseGet(HashMap::new);
//        return constructorDependencies;
    }
}
