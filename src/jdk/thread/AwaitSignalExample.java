package jdk.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignalExample {
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private String message = null;

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalExample example = new AwaitSignalExample();
        executorService.execute(example::consume);
        executorService.execute(example::produce);
        executorService.execute(example::produce);
        executorService.execute(example::consume);

        executorService.awaitTermination(1,TimeUnit.SECONDS);
    }

    public void produce() {
        lock.lock();
        try {
            if (message != null) {
                System.out.println("message is not null,await");
                notFull.await();
            }
            message = "produce";
            System.out.println("produce");
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            if (message == null) {
                System.out.println("message is null ,await");
                notEmpty.await();
            }
            System.out.println("consume");
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
