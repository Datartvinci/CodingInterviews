package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class RandomAccess {
    public static void main(String[] args) {
        new RandomAccess().writeToFile();
    }
    private void writeToFile() {
        RandomAccessFile random = null;
        String fileName = "./textRandom";
        try {
            random = new RandomAccessFile(fileName, "rw");
            random.writeByte(0);
            random.writeBytes("bytes");
            random.writeInt(1);
            random.writeBoolean(true);
            random.writeChars("chars");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Util.closeQuietely(random);
        }
    }
}
