package com.sv.cache.storage;

import com.sv.cache.CacheFullException;

public interface Storage<Key, Value> {

    int capacity = 5;

    public Value get(Key key);
    public void put(Key key, Value value) throws CacheFullException;
    public void remove(Key key);
    void print();

}
