package codeinterview;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

/**
 * Created by John on 2016/10/15.
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Solution.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
     }
     @Test
    public void testArray(){
        Integer[] a=new Integer[20];
         System.out.println(a.length);
         System.out.println(a[1]);
     }


}
