package io;

import java.io.IOException;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class InputFromConsole {
    public static void main(String[] args) throws IOException {
        int size;
        byte[] buffer=new byte[1024];
        size=System.in.read(buffer);
        System.out.println(new String(buffer,0,size));
    }
}
