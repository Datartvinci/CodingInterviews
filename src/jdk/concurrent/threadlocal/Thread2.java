package jdk.concurrent.threadlocal;

import java.util.WeakHashMap;

public class Thread2 extends Thread {
    //ThreadLocal作为key，一个ThreadLocal代表一种线程私有变量
    WeakHashMap<ThreadLocal2, Object> threadLocals;
}

class ThreadLocal2<T> {
    //模板方法
    protected T initialValue() {
        return null;
    }

    public T get() {
        Thread2 thread = (Thread2) Thread.currentThread();
        WeakHashMap<ThreadLocal2, Object> threadLocalMap = thread.threadLocals;
        if (threadLocalMap != null) {
            Object value = threadLocalMap.get(this);
            if (value != null) {
                return (T) value;
            }
        }
        T value = initialValue();
        if (threadLocalMap != null) {
            threadLocalMap.put(this, value);
        } else {
            //懒加载，大部分线程不使用threadLocal，因此能节约不少内存
            thread.threadLocals = new WeakHashMap<>();
            thread.threadLocals.put(this, value);
        }
        return value;
    }

    public void set(T object) {
        Thread2 thread = (Thread2) Thread.currentThread();
        WeakHashMap<ThreadLocal2, Object> threadLocalMap = thread.threadLocals;
        if (threadLocalMap != null) {
            threadLocalMap.put(this, object);
        } else {
            //懒加载，大部分线程不使用threadLocal，因此能节约不少内存
            thread.threadLocals = new WeakHashMap<>();
            thread.threadLocals.put(this, initialValue());
        }
    }
}
