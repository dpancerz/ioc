package com.dpancerz.ioc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DependencyInjectionTest {
    @Test
    public void should_inject_bean_into_another() {
        IocEngine iocEngine = IocEngineFactory.forPackage("com.dpancerz.ioc");

        Application application = iocEngine.buildTheApplication();

        assertNotNull(application.getBean(InjectableBean.class));
        ClassWithInjectedBean classWithInjectedBean = application.getBean(ClassWithInjectedBean.class);
        assertNotNull(classWithInjectedBean);
        assertNotNull(classWithInjectedBean.getInjectableBean());
    }
}
