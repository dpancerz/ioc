package com.dpancerz.ioc;

import java.util.Set;

interface DirectedManyRelationsGraph<T, R> {
    void addEdge(T from, T to, R relation);
    void removeEdge(T from, T to, R relation);
    void addNode(T node);
    void removeNode(T node);
    Set<T> adjacentNodes(T node);
    Set<T> successorsForRelation(T node, R relation);
}
