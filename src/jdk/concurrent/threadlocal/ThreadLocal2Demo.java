package jdk.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocal2Demo {
    private static final ThreadLocal2<SimpleDateFormat> threadLocal = new ThreadLocal2<>() ;

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
