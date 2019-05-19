package jdk.sourcecode;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class StringTest {
    public static void main(String[] args) {
        String a = "abc";
        String b = new String("abc").intern();
        String c = new String("abc");
        String d = new String(c.toCharArray());
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c == b);
        System.out.println(c == d);
        Assert.assertTrue("abc" == new String("abc").intern());
    }

    @Test
    public void testIntern() {
        String a = new String("abc");
        System.out.println("abc" == a);
        System.out.println("abc" == a.intern());

    }

    @Test
    public void testStringPool() {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 2000; i++) {
            list.add(String.valueOf(i).intern());
        }
    }

    @Test
    public void testHashCode() {
        String a = "abc";
        System.out.println(a.hashCode());
    }

    @Test
    public void testToLowerCase() {
        String a = "aBc";
        System.out.println(a.toLowerCase());
    }

    @Test
    public void testCompareTo() {
        System.out.println("abc".compareTo("ab"));
        System.out.println("abc".compareTo("ad"));
        System.out.println("abc".compareTo("d"));
        System.out.println("abc".compareTo("abd"));
        System.out.println("abc".compareTo("aaaa"));
    }

    boolean isOdd = false;
    int i = 0;

    public synchronized void odd() {
        while (i <= 100) {
            while (isOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            i++;
            isOdd = true;
            if(i>100){
                notify();
                break;
            }
            System.out.println("odd" + i);
            notify();
        }
    }

    public synchronized void even() {
        System.out.println("even get lock");
        while (i <= 100) {
            while (!isOdd) {
                try {
                    System.out.println("even release lock");
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("even interrupt");
                    return;
                }
            }
            isOdd = false;
            i++;
            if(i>100){
                notify();
                break;
            }
            System.out.println("even" + i);
            notify();
        }
    }
    public synchronized void doNothing()  {
        System.out.println("doNothing get lock");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("doNothing interrupt");
        }
        System.out.println("doNothing release lock");
    }
    @Test
    public void testConcurrent() throws InterruptedException {
//        new Thread(()->odd()).start();
        Thread t2 = new Thread(() -> even());
        t2.start();
        new Thread(()->doNothing()).start();
        Thread.sleep(100);
        t2.interrupt();
        System.out.println("interrupt");
        t2.join();
    }
}
