package com.dpancerz.ioc;

import java.util.Objects;

class BeanInfo {
    private final Class<?> clazz;
    private final String name;

    BeanInfo(Class<?> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanInfo that = (BeanInfo) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, name);
    }
}
