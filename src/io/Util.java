package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class Util {
    public static void closeQuietely(InputStream in)
    {
        if(in!=null)
        {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeQuietely(OutputStream out) {
        if(out!=null)
        {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeQuietely(RandomAccessFile random) {
        if(random!=null){
            try {
                random.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
