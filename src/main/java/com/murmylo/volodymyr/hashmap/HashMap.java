package com.murmylo.volodymyr.hashmap;

import java.util.Collection;

public interface HashMap<K, V> {
    V put(K key, V value);

    Collection<K> keySet();

    Collection<V> values();

    V get(K key);
}
