package com.dpancerz.ioc;

class IocEngineFactory {
    static IocEngine forPackage(String rootPackage) {
        return new IocEngine(
                rootPackage,
                new ReflectionsAnnotationProcessor(),
                new SimpleApplicationFactory(
//                        classReflections
                        new GuavaDirectedNetworkFactory(),
                        new JavaxDialect(),
                        null

                        //                        dependenciesFinders,
//                        accumulator
                ),
                new JavaxDialect()
        );
    }
}
