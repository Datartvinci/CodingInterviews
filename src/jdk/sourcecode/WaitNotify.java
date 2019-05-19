package jdk.sourcecode;

import org.junit.Test;

public class WaitNotify {
    @Test
    public void testTwoThreadPrint() throws InterruptedException {
        new TwoThreadPrint().run();
    }

    @Test
    public void testNThreadPrint() throws InterruptedException {
        new NThreadPrint().run();
    }

    @Test
    public void testProducerConsumer() throws InterruptedException {
        new ProducerConsumer().run();
    }

    @Test
    public void testNWait() throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("开始等待1");
                synchronized (obj) {
                    obj.wait();
                }
                System.out.println("结束等待1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("开始等待2");
                synchronized (obj) {
                    obj.wait();
                }
                System.out.println("结束等待2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        new Thread(() -> {
            synchronized (obj) {
                obj.notifyAll();
                System.out.println("通知1");
            }
        }).start();

        t1.join();
        t2.join();
    }

    class ProducerConsumer {
        private String message;

        private boolean empty = true;

        void run() throws InterruptedException {
            Thread t1 = new Thread(() -> System.out.println("take:" + take()));
            Thread t2 = new Thread(() -> put("message1"));
            t1.start();
            t2.start();
            t1.join();
        }

        public synchronized String take() {
            while (empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            empty = true;
            notifyAll();
            return message;
        }

        public synchronized void put(String message) {
            while (!empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            empty = false;
            this.message = message;
            notifyAll();
        }
    }

    class TwoThreadPrint {
        void run() throws InterruptedException {
            new Thread(() -> odd()).start();
            Thread t2 = new Thread(() -> even());
            t2.start();
            Thread.sleep(100);
            t2.join();
        }

        boolean isOdd = false;
        int i = 0;

        synchronized void odd() {
            while (i <= 100) {
                while (isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                i++;
                isOdd = true;
                if (i > 100) {
                    notify();
                    break;
                }
                System.out.println("odd" + i);
                notify();
            }
        }

        synchronized void even() {
            while (i <= 100) {
                while (!isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                isOdd = false;
                i++;
                if (i > 100) {
                    notify();
                    break;
                }
                System.out.println("even" + i);
                notify();
            }
        }

    }


    class NThreadPrint {
        void run() throws InterruptedException {
            int n = 10;
            for (int i = 1; i <= n; i++) {
                Thread thread = new Thread(new Task(i, n));
                thread.start();
                if (i == n) {
                    thread.join();
                }
            }
        }

        final Object obj = new Object();
        int num = 1;
        int lastThread = 0;

        class Task implements Runnable {
            final int index;
            final int n;

            Task(int i, int j) {
                index = i;
                n = j;
            }

            @Override
            public void run() {
                synchronized (obj) {
                    while (num <= 100) {
                        while (lastThread % n != index - 1) {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (num > 100) {
                            return;
                        }
                        System.out.println("thread:" + index + ",print:" + num);
                        num++;
                        lastThread++;
                        obj.notifyAll();
                    }
                }
            }
        }
    }

}
