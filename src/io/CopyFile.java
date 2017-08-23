package io;

import java.io.*;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class CopyFile {
    public static void main(String[] args) {
        CopyFile copyFile=new CopyFile();
        long start=System.currentTimeMillis();
        copyFile.copy("./text","./textCopy");
        long end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));
         start=System.currentTimeMillis();
        copyFile.bufferedCopy("./text","./textCopy");
         end=System.currentTimeMillis();
        System.out.println("cost time:"+(end-start));
    }
    private void bufferedCopy(String src,String des){
        File srcFile = new File(src);
        File desFile = new File(des);
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin=new BufferedInputStream(new FileInputStream(srcFile));
            bout=new BufferedOutputStream(new FileOutputStream(desFile));
            byte[] buffer=new byte[1024];
            while(bin.read(buffer)!=-1){
                bout.write(buffer);
            }
            bout.flush();
            System.out.println("copied [" + srcFile.getName() + "]    with    "
                    + srcFile.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Util.closeQuietely(bin);
            Util.closeQuietely(bout);
        }
    }

    private void copy(String src,String des)
    {
        InputStream in=null;
        OutputStream out=null;
        try {
            File srcFile=new File(src);
            in=new FileInputStream(srcFile);
            out =new FileOutputStream(des);
            byte[] buffer=new byte[(int) srcFile.length()];
            //这个做法比下面的做法快200倍
            int size=in.read(buffer);
            /*
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) in.read();
            }*/
            out.write(buffer);
            System.out.println("copied [" + srcFile.getName() + "]    with    "
                    + srcFile.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                Util.closeQuietely(in);
                Util.closeQuietely(out);
        }
    }

}
