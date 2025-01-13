package com.sv.cache.policies;

import com.sv.cache.CacheFullException;

public interface AccessPolicy<Key> {
    void accessed(Key key) throws CacheFullException;
    void remove(Key key);
    Key evict();

    void print();
}
