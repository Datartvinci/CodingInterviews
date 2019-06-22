package jdk.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            threadLocal.set(simpleDateFormat);
        }
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
}
