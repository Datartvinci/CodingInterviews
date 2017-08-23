package io;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Datartvinci on 2017/1/8.
 * 列出给出路径下所有的目录，包括子目录
 */
public class ListDir {
    public static void main(String[] args) {
        String fileName= "."+File.separator;
        File file=new File(fileName);
        File[] files = file.listFiles();
        Arrays.stream(files).forEachOrdered(f -> {
            System.out.println(f.getName());
        });
    }
}
