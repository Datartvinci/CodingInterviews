package jdk.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalInsideDemo {

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
}
