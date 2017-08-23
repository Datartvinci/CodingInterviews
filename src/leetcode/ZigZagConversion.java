package leetcode;

import org.junit.Assert;

import java.util.ArrayList;

/**
 * Created by Datartvinci on 2017/5/24.
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigZagConversion {
    public static void main(String[] args) {
        Assert.assertEquals(convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
    }

    public static String convert(String s, int numRows) {
        ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            stringBuilders.add(new StringBuilder());
        }
        char[] chars = s.toCharArray();
        boolean flag = false;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = index % (numRows);
            StringBuilder sb = stringBuilders.get(index);
            sb.append(chars[i]);
            if (index == 0) {
                flag = true;
            }else if(index==numRows-1){
                flag=false;
            }
            if (flag) {
                ++index;
            } else {
                --index;
            }

        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            StringBuilder sb = stringBuilders.get(i);
            result.append(sb.toString());
        }
        return result.toString();
    }
}
