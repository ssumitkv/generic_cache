package com.sv.cache.storage;

import com.sv.cache.CacheFullException;
import com.sv.cache.policies.AccessPolicy;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value>{

    final Map<Key, Value> storage;

    AccessPolicy<Key> accessPolicy;

    public HashMapBasedStorage(AccessPolicy<Key> accessPolicy) {
        storage = new HashMap<>();
        this.accessPolicy = accessPolicy;

    }


    @Override
    public Value get(Key key) {
        try {
            accessPolicy.accessed(key);
        } catch (CacheFullException e) {
            throw new RuntimeException(e);
        }
        return storage.get(key);
    }

    @Override
    public void put(Key key, Value value)  {
        try {
            accessPolicy.accessed(key);
            storage.put(key,value);
        } catch (CacheFullException e) {
            Key evictedkey = accessPolicy.evict();
            remove(evictedkey);
            try {
                accessPolicy.accessed(key);
            } catch (CacheFullException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void remove(Key key) {
        storage.remove(key);
        accessPolicy.remove(key);
    }

    @Override
    public void print() {
        System.out.println("*************** PRINTING DATA ****************");
        for(Map.Entry<Key, Value> entry : storage.entrySet()){
            System.out.println("Key: "+entry.getKey()+" : Value: "+entry.getValue());
        }
        System.out.println("*************** END ****************");

    }
}
