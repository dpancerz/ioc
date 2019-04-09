package com.dpancerz.ioc;

import javax.inject.Inject;

class ClassWithInjectedBean {
    @Inject
    private InjectableBean injectableBean;

    public InjectableBean getInjectableBean() {
        return injectableBean;
    }
}
