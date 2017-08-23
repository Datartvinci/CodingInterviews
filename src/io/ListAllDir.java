package io;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class ListAllDir {
    private void print(File file){
        if(file==null)
            return;
        if(file.isDirectory()){
            System.out.println(file.getName());
            File[] files = file.listFiles();
            if(files==null)return;
            Arrays.stream(files).forEachOrdered(this::print);
        }else{
            System.out.println(file.getName());
        }
    }

    public static void main(String[] args) {
        ListAllDir dir=new ListAllDir();
        dir.print(new File("/Library/Python"));
    }
}
