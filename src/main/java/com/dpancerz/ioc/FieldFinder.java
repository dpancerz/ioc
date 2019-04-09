package com.dpancerz.ioc;

import java.lang.reflect.Field;
import java.util.Collection;

class FieldFinder extends MemberFinder<Field> {
    Collection<Field> findAllFields(Class<?> clazz) {
        return super.findAllMembers(clazz);
    }

    @Override
    Field[] getMembersOfCurrentClass(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }
}
