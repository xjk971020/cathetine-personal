package com.cathetine.utils;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteMap<K,V> {

    private Map<K,V> map;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ReadWriteMap(Map<K, V> map) {
        this.map = map;
    }



    public V get(K key) {

        try {
            lock.readLock().lock();
            return map.get(key);
        }finally {
            lock.readLock().unlock();
        }

    }

    public void put(K key, V value) {
        try {
            lock.writeLock().lock();
            map.put(key, value);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public boolean containsKey(K key){
        try {
            lock.readLock().lock();
            return map.containsKey(key);
        }finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 同步操作
     * @param operation
     */
    public<R> R syncOperate(Operation<K,V,R> operation){
        try {
            lock.writeLock().lock();
            return operation.operate(this.map);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public interface Operation<K,V,R>{
        R operate(Map<K, V> map);
    }
}
