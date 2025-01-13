package com.sv.cache.policies;

import com.sv.cache.CacheFullException;
import com.sv.cache.storage.Storage;

import java.util.LinkedList;
import java.util.Map;

public class LRUAccessPolicy<Key> implements AccessPolicy<Key> {

    LinkedList<Key> accessLog;

    public LRUAccessPolicy(LinkedList<Key> accessLog) {
        this.accessLog = accessLog;
    }

    @Override
    public void accessed(Key key) throws CacheFullException {
        if(accessLog.size() == Storage.capacity) {
            throw new CacheFullException("Cache storage is full!!");
        }
        accessLog.remove(key);
        accessLog.addLast(key);
    }

    @Override
    public void remove(Key key) {
        accessLog.remove(key);
    }

    @Override
    public Key evict() {
        return accessLog.removeFirst();
    }

    @Override
    public void print() {
        System.out.println(accessLog);
    }
}
