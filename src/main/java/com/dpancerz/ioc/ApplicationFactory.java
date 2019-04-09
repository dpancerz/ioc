package com.dpancerz.ioc;

import java.util.Set;

interface ApplicationFactory {
    Application createApplication(Set<Class<?>> scannedBeans);
}
