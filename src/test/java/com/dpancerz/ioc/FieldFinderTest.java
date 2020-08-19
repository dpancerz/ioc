package com.dpancerz.ioc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldFinderTest {
    private FieldFinder fieldFinder;
    private static final List<String> EXPECTED_ALL_FIELDS = asList(
            "publicConcreteField",
            "packageConcreteField",
            "protectedConcreteField",
            "privateConcreteField",
            "privateConcreteCollectionField",
            "privateConcreteListField",
            "privateConcreteSetField",
            "privateConcreteMap",
            "publicSuperField",
            "packageSuperField",
            "protectedSuperField",
            "privateSuperField",
            "privateCollectionField",
            "privateListField",
            "privateSetField",
            "privateMap"
    );

    @BeforeEach
    void setUp() {
        fieldFinder = new FieldFinder();
    }

    @Test
    void findAllFields() {
        //given
        Class<Concrete> clazz = Concrete.class;

        //when
        Collection<Field> extractedFields = fieldFinder.findAllFields(clazz);
        List<String> extractedAsStrings = extractedFields.stream().map(Field::getName).collect(toList());

        //then
        assertEquals(EXPECTED_ALL_FIELDS, extractedAsStrings);
        assertTrue(extractedAsStrings.containsAll(EXPECTED_ALL_FIELDS));
        assertEquals(EXPECTED_ALL_FIELDS.size(), extractedFields.size());
    }

    public static abstract class Abstract {
        public Integer publicSuperField;
        Long packageSuperField;
        protected Float protectedSuperField;
        private String privateSuperField;
        private Collection<String> privateCollectionField;
        private List<String> privateListField;
        private TreeSet<String> privateSetField;
        private Map<String, Abstract> privateMap;
    }

    public static class Concrete extends Abstract {
        public Integer publicConcreteField;
        Long packageConcreteField;
        protected Float protectedConcreteField;
        private String privateConcreteField;
        private Collection<String> privateConcreteCollectionField;
        private List<String> privateConcreteListField;
        private TreeSet<String> privateConcreteSetField;
        private Map<String, Abstract> privateConcreteMap;
    }
}