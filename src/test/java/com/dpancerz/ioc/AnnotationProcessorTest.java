package com.dpancerz.ioc;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationProcessorTest {
    @Test
    public void dupa() throws Exception {
        Field field = Cls.class.getDeclaredField("field");

        assertEquals(String.class, field.getType());
        assertEquals(Cls.class, field.getDeclaringClass());
    }

    public static class Cls {
        private String field;
    }
}
