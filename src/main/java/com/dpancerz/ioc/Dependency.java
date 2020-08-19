package com.dpancerz.ioc;

import java.util.Objects;

class Dependency {
    private final BeanInfo from;
    private final BeanInfo to;
    private final DependencyType type;

    Dependency(
            final BeanInfo from,
            final BeanInfo to,
            final DependencyType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    BeanInfo from() {
        return from;
    }

    BeanInfo to() {
        return to;
    }

    DependencyType type() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Dependency that = (Dependency) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, type);
    }
}
