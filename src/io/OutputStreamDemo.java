package io;

import java.io.*;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class OutputStreamDemo {
    public static void main(String[] args) {
        OutputStreamDemo demo=new OutputStreamDemo();
        demo.writeWithByte("."+ File.separator+"text");
    }
    private void writeWithByte(String fileName)
    {
        OutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(fileName);
            String str="I am a computer scientist\n";
            for(int i=0;i<900000;++i)
            {
            outputStream.write(str.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
