package com.dpancerz.ioc;

import java.util.Collection;

class GuavaDirectedNetworkFactory implements DirectedManyRelationsGraphFactory {
    @Override
    public <T, R> DirectedManyRelationsGraph<T, R> graphWithoutEdges(Collection<T> nodes, Class<R> edgesClass) {
        GuavaDirectedNetwork<T, R> graph = new GuavaDirectedNetwork<>();
        nodes.forEach(graph::addNode);
        return graph;
    }
}
