package com.cathetine.utils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁同步一个对象
 * @param <T>
 */
public class ReadWriteObject<T> {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final T object;

    public ReadWriteObject(T object) {
        this.object = object;
    }

    /**
     * 使用读锁
     * @param operation 读操作
     * @param <R>
     * @return
     */
    public <R> R readOperate(Operation<T, R> operation) {
        try {
            lock.readLock().lock();
            return operation.operate(object);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 使用写锁
     * @param operation 写操作
     * @param <R>
     * @return
     */
    public <R> R writeOperate(Operation<T, R> operation) {
        try {
            lock.writeLock().lock();
            return operation.operate(object);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public interface Operation<V, R> {
        R operate(V obj);
    }
}
