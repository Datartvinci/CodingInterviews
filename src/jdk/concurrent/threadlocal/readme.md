# 1. ThreadLocal

## 1.1 简介

ThreadLocal是线程内部的数据存储类，通过它可以指定的线程中存储数据，数据存储以后，只有在指定线程中可以获取到存储的数据，对于其他线程来说则无法获取数据。

它能够满足以下需求:

- 同一个变量在不同的线程中需要有不同的副本
- 经常应用于static方法,无法在线程创建的时候赋值

## 1.2 应用场景

- 数据库连接池
- Hibernate的session
- 其他线程不安全的类在多线程中不加锁使用

## 1.3 应用示例

时间解析类SimpleDateFormat是线程不安全的典型例子，因为同一时刻下只能支持一个解析表达式，多线程环境下使用只有两种选择：

- 加锁访问
- 通过ThreadLocal，让线程持有一个线程私有的SimpleDateFormat对象

```java

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            threadLocal.set(simpleDateFormat);
        }
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
```



# 2. 设计原理

接下来我们尝试着自己设计一个方案，让线程随时随地可以拥有自己的线程私有变量。通过设计方案的演变来帮助理解JDK的方案。

线程私有变量有两个保存地方可以选择：

- 线程内部
- 外部集合

## 2.1 保存在线程内

### 2.1.1 定义

最先想到的办法，也是最直白的，是通过Map来持有多个线程私有变量。

```java
public class ThreadLocalInside extends Thread {
    WeakHashMap<Object, Object> threadLocals = new WeakHashMap<>();
}
```



### 2.1.2 使用样例

自己灵活的设置key来区分不同的线程私有变量。

```java
    static String format(Date date, String pattern) {
        ThreadLocalInside thread = (ThreadLocalInside) Thread.currentThread();
        Class clazz = SimpleDateFormat.class;
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) thread.threadLocals.get(clazz);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            thread.threadLocals.put(clazz, simpleDateFormat);
        }
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
```



### 2.1.3 优点

- 线程安全。不会有多线程访问的问题。

### 2.1.4 缺点

- 使用不当容易内存占用过大。私有变量存活时间与线程一样长，无法利用上垃圾收集器。
- 耦合。新增的API需要耦合在线程类中。
- 管理不方便。如果要移除某个线程私有变量在所有线程中的副本，十分困难。

## 2.2 保存在外部集合

### 2.2.1 代码实现

```java
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
```



### 2.2.2 使用样例

```java
    private static final ThreadLocalOutside THREAD_LOCAL_MANAGER = new ThreadLocalOutside();

    static String format(Date date, String pattern) {
        Thread thread = Thread.currentThread();
        HashMap<Object, Object> threadLocalVariable = THREAD_LOCAL_MANAGER.get(thread);
        Class clazz = SimpleDateFormat.class;
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) threadLocalVariable.get(clazz);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            threadLocalVariable.put(clazz, simpleDateFormat);
        }
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
```



### 2.2.3 优点

- 解耦。使用单独外部类来管理线程私有变量，不必加入线程api中。
- 管理方便。可以轻松移除所有线程中的某个私有变量。

### 2.2.4 缺点

- 性能问题。需要加锁避免线程不安全问题。
- 使用不当容易内存占用过大。私有变量存活时间与线程一样长，无法利用上垃圾收集器。

# 3. JDK设计，内外结合

JDK的ThreadLocal包含了上面两种设计思想，即保存在线程内，却通过外部类提供API来操作。

新引入了一个设计：使用ThreadLocal对象作为私有变量集合的Key，每一个ThreadLocal对象代表一个种类的线程私有变量。这个设计解决了线程私有变量管理的痛点：

- 当你需要有多少种线程私有变量，就创建多少个ThreadLocal对象，让线程私有变量管理起来比较方便。
- 想让某个线程私有变量在所有线程中移除时，不再引用ThreadLocal对象即可，其他交给垃圾收集器。

## 3.1 代码实现

为了不让读者的注意力被弱引用散列表的实现细节分散，这里使用WeakHashMap代替ThreadLocal内部的ThreadLocalMap。

### 3.1.1 弱引用集合

简单介绍以下这两个集合功能:

- 如果集合中的Key不再被其他对象引用，垃圾收集器会回收掉这个Key对象。
- 调用put()、get()等方法时，会顺便删除散列表里的元素，条件是Key对象已经被垃圾回收。
- 散列冲突时，WeakHashMap使用链表法，ThreadLocalMap使用开放定址法解决冲突(使用数组来节约内存)，优点是实现简单。

### 3.1.2 山寨版JDK实现

```java
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
```

## 3.2 使用样例

使用起来几乎和JDK实现的ThreadLocal一模一样呢

```java
    private static final ThreadLocal2<SimpleDateFormat> threadLocal = new ThreadLocal2<>() ;

    static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            threadLocal.set(simpleDateFormat);
        }
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
```

## 3.3 优点

- 高性能:线程私有变量保存在线程内的集合中，不需要加锁。
- 解耦:外部对象提供API。
- 利用JDK垃圾回收机制：外部对象作为线程内散列表的Key，但是用弱引用持有Key，当外部对象不再被其他对象引用且已经被垃圾收集器回收后，会顺便删除散列表里的元素，条件是Key对象已经被垃圾回收。
- 管理方便。可以轻松移除所有线程中的某个私有变量，只需要删除对应threadLocal的引用即可。

简直是去其糟粕取其精华。

## 3.4 缺点

- 使用不当容易内存占用过大。私有变量存活时间与线程一样长。

