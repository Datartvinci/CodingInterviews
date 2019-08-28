package jdk.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {
    @Test
    public void testSync() {
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e1::func1);
    }
    @Test
    public void testSync2() {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func2);
        executorService.execute(e2::func2);
    }

    @Test
    public void testNotSync() {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e2::func1);
    }

    public void func1() {
        //作用于对象
        synchronized (this) {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void func2() {
        //作用于整个类
        synchronized (SynchronizedExample.class) {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    //作用于整个类
    public synchronized static void fun() {
        // ...
    }
}
