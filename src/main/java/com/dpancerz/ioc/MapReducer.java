package com.dpancerz.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

class MapReducer<K, V> implements BinaryOperator<Map<K, V>> {

    @Override
    public Map<K, V> apply(Map<K, V> a,
                           Map<K, V> b) {
        Map<K, V> result = new HashMap<>();
        result.putAll(a);
        result.putAll(b);
        return result;
    }
}
