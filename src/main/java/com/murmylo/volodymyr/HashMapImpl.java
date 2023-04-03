package com.murmylo.volodymyr;

import com.murmylo.volodymyr.hashmap.HashMap;
import lombok.AllArgsConstructor;

import java.util.*;

public class HashMapImpl<K, V> implements HashMap<K, V> {

    public static final int DEFAULT_CAPACITY = 8;
    private final int size;
    private final HashMapEntry<K, V>[] table;

    public HashMapImpl(int size) {
        this.table = new HashMapEntry[size];
        this.size = size;
    }

    public HashMapImpl() {
        this.size = DEFAULT_CAPACITY;
        this.table = new HashMapEntry[size];
    }

    @AllArgsConstructor
    static class HashMapEntry<K, V> {
        final K key;
        V value;
        HashMapEntry<K, V> next;

        public HashMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V put(K key, V value) {
        int hash = hash(key);
        int index = getIndex(hash);
        return putVal(index, key, value);
    }

    public Collection<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            HashMapEntry<K, V> entry = table[i];
            if (entry != null) {
                HashMapEntry<K, V> temp = entry;
                while (temp != null) {
                    keySet.add(entry.key);
                    temp = temp.next;
                }
            }
        }
        return keySet;
    }

    public Collection<V> values() {
        Set<V> values = new HashSet<>();
        for (int i = 0; i < size; i++) {
            HashMapEntry<K, V> entry = table[i];
            if (entry != null) {
                HashMapEntry<K, V> temp = entry;
                while (temp != null) {
                    values.add(entry.value);
                    temp = temp.next;
                }
            }
        }
        return values;
    }


    private int getIndex(int hash) {
        return hash & size - 1;
    }

    private V putVal(int index, K key, V value) {
        HashMapEntry<K, V> tableEntry = table[index];
        HashMapEntry<K, V> newEntry = new HashMapEntry<>(key, value);
        if (tableEntry == null) {
            table[index] = newEntry;
            return null;
        }
        Optional<HashMapEntry<K, V>> existingEntry = existingEntry(key, tableEntry);
        if (existingEntry.isPresent()) {
            HashMapEntry<K, V> entry = existingEntry.get();
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
        HashMapEntry<K, V> temp;
        temp = tableEntry;
        tableEntry = newEntry;
        tableEntry.next = temp;
        table[index] = tableEntry;
        return temp.value;
    }

    private Optional<HashMapEntry<K, V>> existingEntry(K key, HashMapEntry<K, V> tableEntry) {
        HashMapEntry<K, V> entry = tableEntry;
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return Optional.of(entry);
            }
            entry = entry.next;
        }
        return Optional.empty();
    }

    public V get(K key) {
        int index = getIndex(hash(key));
        HashMapEntry<K, V> temp = table[index];
        while (temp != null) {
            if (Objects.equals(temp.key, key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }
}
