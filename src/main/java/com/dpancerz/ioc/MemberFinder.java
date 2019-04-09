package com.dpancerz.ioc;

import com.google.common.collect.ImmutableList;

import java.lang.reflect.Member;
import java.util.Collection;

import static java.util.Arrays.asList;

abstract class MemberFinder<T extends Member> {
    Collection<T> findAllMembers(Class<?> clazz) {
        ImmutableList.Builder<T> builder = ImmutableList.builder();
        while (!Object.class.equals(clazz)) { //extract to member finder
            builder.addAll(asList(getMembersOfCurrentClass(clazz)));
            clazz = clazz.getSuperclass();
        }
        return builder.build();
    }

    abstract T[] getMembersOfCurrentClass(Class<?> clazz);
}
