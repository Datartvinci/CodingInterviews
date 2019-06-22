package jdk.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ThreadLocalOutsideDemo {
    private static final ThreadLocalOutside THREAD_LOCAL_MANAGER = new ThreadLocalOutside();

    static String format(Date date, String pattern) {
        Thread thread = Thread.currentThread();
        HashMap<Object, Object> threadLocalVariable = THREAD_LOCAL_MANAGER.get(thread);
        Class clazz = SimpleDateFormat.class;
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) threadLocalVariable.get(clazz);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            threadLocalVariable.put(clazz, simpleDateFormat);
        }
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
}
