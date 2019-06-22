package jdk.concurrent.threadlocal;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadLocalOutside extends WeakHashMap<Thread, HashMap<Object, Object>> {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public HashMap<Object, Object> put(Thread key, HashMap<Object, Object> value) {
        lock.writeLock().lock();
        try {
            return super.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public HashMap<Object, Object> get(Thread key) {
        lock.readLock().lock();
        try {
            return super.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
}
