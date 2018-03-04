package leetcode;

/**
 * Created by Datartvinci on 2017/11/15.
 */
public class CountAndSay {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++)
            System.out.println(countAndSay(i));
    }

    public static String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            char prev = s.charAt(0);
            String temp = "";
            int count = 1;
            for (int j = 1; j < s.length(); j++) {
                char curr = s.charAt(j);
                if (curr == prev) {
                    count++;
                } else {
                    temp += String.valueOf(count) + prev;
                    count = 1;
                }
                if (j==s.length()-1) {
                    temp+=String.valueOf(count)+curr;
                } else {
                    prev = curr;
                }
            }
            if (temp.length() == 0) {
                temp = String.valueOf(count) + prev;
            }
            s = temp;
        }
        return s;
    }
}
