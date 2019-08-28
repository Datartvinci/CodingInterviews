package jdk.thread;

import org.junit.Test;

public class BasicThreadExample {
    @Test
    public void testDaemon() throws InterruptedException {
        Thread thread = new Thread(()-> System.out.println("thread run"));
        thread.setDaemon(true);
        thread.join();
    }
    @Test
    public void testSleep() throws InterruptedException {
        Thread thread = new Thread(()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //异常不能跨线程传播
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.join();
    }
}
