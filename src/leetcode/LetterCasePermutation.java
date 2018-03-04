package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Datartvinci on 2018/2/22.
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 * <p>
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * <p>
 * Input: S = "12345"
 * Output: ["12345"]
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        new LetterCasePermutation().letterCasePermutation("a1b2").forEach(System.out::println);
        new LetterCasePermutation().letterCasePermutation("3z4").forEach(System.out::println);
        new LetterCasePermutation().letterCasePermutation("123456").forEach(System.out::println);
        new LetterCasePermutation().letterCasePermutation("C").forEach(System.out::println);
    }

    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        if (S == null) {
            return list;
        }
        if (S.isEmpty()) {
            list.add(S);
            return list;
        }
        List<String> childList = letterCasePermutation(S.substring(1, S.length()));
        for (String s : childList) {
            char c = S.charAt(0);
            String first;
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                first = String.valueOf(c).toLowerCase();
                list.add(first + s);
                first = String.valueOf(c).toUpperCase();
                list.add(first + s);
            } else {
                list.add(c + s);
            }
        }
        return list;
    }
}
