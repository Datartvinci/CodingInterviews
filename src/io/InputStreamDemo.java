package io;

import java.io.*;

/**
 * Created by Datartvinci on 2017/1/8.
 * 从外部读入一个文件
 */
public class InputStreamDemo {
    public static void main(String[] args) {
        new InputStreamDemo().readFile("./text");
    }
    private void readFile(String fileName){
        File srcFile=new File(fileName);
        InputStream in=null;
        try {
            in=new FileInputStream(srcFile);
            byte[] buffer=new byte[(int) srcFile.length()];
            for(int i=0;i<buffer.length;++i)
            {
                buffer[i]= (byte) in.read();
            }
            System.out.println(new String(buffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(in!=null)
                {
                    in.close();
                    in=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
