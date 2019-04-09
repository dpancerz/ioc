package com.dpancerz.ioc;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;

import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

class GuavaDirectedNetwork<T, R> implements DirectedManyRelationsGraph<T, R> {
    private final MutableNetwork<T, Relation<T, R>> network;

    public GuavaDirectedNetwork() {
         network = NetworkBuilder.directed()
                 .allowsParallelEdges(true)
                 .allowsSelfLoops(true)
                 .build();
    }

    @Override
    public void addEdge(T from, T to, R relationType) {
        network.addEdge(from, to, new Relation<>(from, to, relationType));
    }

    @Override
    public void removeEdge(T from, T to, R relationType) {
        network.removeEdge(new Relation<>(from, to, relationType));
    }

    @Override
    public void addNode(T node) {
        network.addNode(node);
    }

    @Override
    public void removeNode(T node) {
        network.removeNode(node);
    }

    @Override
    public Set<T> adjacentNodes(T node) {
        return network.adjacentNodes(node);
    }

    @Override
    public Set<T> successorsForRelation(T node, R relation) {
        return network.incidentEdges(node)
                .stream()
                .filter(edge -> startingFromNode(node, edge))
                .filter(edge -> edge.relationType.equals(relation))
                .map(Relation::getTo)
                .collect(toSet());
    }

    private boolean startingFromNode(T node, Relation<T, R> edge) {
        return edge.from.equals(node);
    }

    private static class Relation<T, R> {
        private final T from;
        private final T to;
        private final R relationType;

        private Relation(T from, T to, R relationType) {
            this.from = requireNonNull(from);
            this.to = requireNonNull(to);
            this.relationType = requireNonNull(relationType);
        }

        public T getFrom() {
            return from;
        }

        public T getTo() {
            return to;
        }

        public R getRelationType() {
            return relationType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Relation<?, ?> relation = (Relation<?, ?>) o;
            return Objects.equals(from, relation.from) &&
                    Objects.equals(to, relation.to) &&
                    Objects.equals(relationType, relation.relationType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, relationType);
        }
    }

}
