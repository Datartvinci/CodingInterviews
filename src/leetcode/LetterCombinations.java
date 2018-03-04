package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Datartvinci on 2017/8/23.
 * Given a digit string, return all possible letter combinations that the number could represent.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinations {
    static Map<String, String> map = new HashMap<>();


    public static void main(String[] args) {
        new LetterCombinations().letterCombinations("23").forEach(System.out::print);
    }

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        ans.add("");
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for (int i = 0; i < digits.length(); i++) {
            String letters = mapping[(int) digits.charAt(i)-48];
            while (ans.peek().length() == i) {
                String prefix=ans.poll();
                for (char letter : letters.toCharArray()) {
                    ans.add(prefix + letter);
                }
            }

        }
        return ans;
    }
}
