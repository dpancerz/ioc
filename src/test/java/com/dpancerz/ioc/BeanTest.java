package com.dpancerz.ioc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanTest {

    @Test
    void string_can_be_passed_as_object() {
        new Bean(new BeanInfo(Object.class, "bean"), "dupa");
    }

    @Test
    void object_can_not_be_passed_as_string() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bean(
                        new BeanInfo(String.class, "bean"),
                        new Object())
        );
    }
}