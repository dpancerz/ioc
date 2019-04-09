package com.dpancerz.ioc;

import java.util.Collection;

interface DirectedManyRelationsGraphFactory {
    <T, R> DirectedManyRelationsGraph<T, R> graphWithoutEdges(Collection<T> nodes, Class<R> edgesClass);
}
