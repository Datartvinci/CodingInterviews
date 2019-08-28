package jdk.thread;

import org.junit.Test;

public class JoinExample {
    @Test
    public void test() throws InterruptedException {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
        b.join();
    }
    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

}
