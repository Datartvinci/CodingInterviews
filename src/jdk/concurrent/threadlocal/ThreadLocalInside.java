package jdk.concurrent.threadlocal;

import java.util.WeakHashMap;

public class ThreadLocalInside extends Thread {
    WeakHashMap<Object, Object> threadLocals = new WeakHashMap<>();
}
