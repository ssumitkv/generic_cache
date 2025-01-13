package com.sv.cache;

import com.sv.cache.exceptions.CacheFullException;
import com.sv.cache.policies.LRUAccessPolicy;
import com.sv.cache.storage.HashMapBasedStorage;
import com.sv.cache.storage.Storage;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) throws CacheFullException {
        Storage<String, String> storage = new HashMapBasedStorage<>(new LRUAccessPolicy<>(new LinkedList<>()));

        storage.put("K1", "V1");
        storage.put("K2", "V1");
        storage.put("K3", "V1");
        storage.print();

        storage.put("K4", "V1");
        storage.put("K5", "V1");
        storage.put("K6", "V1");
        storage.print();


    }
}