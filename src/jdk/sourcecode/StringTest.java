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


}
