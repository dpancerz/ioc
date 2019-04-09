package com.dpancerz.ioc;

import java.lang.reflect.Method;
import java.util.Collection;

class MethodFinder extends MemberFinder<Method> {
    Collection<Method> findAllMethods(Class<?> clazz) {
        return super.findAllMembers(clazz);
    }

    @Override
    Method[] getMembersOfCurrentClass(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }
}
