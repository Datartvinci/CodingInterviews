package leetcode;


import org.junit.Test;

/**
 * Created by Datartvinci on 2017/6/1.
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.</p>
 */
public class LongestCommonPrefix {
    @Test
    public void test() {
        String commonPrefix = longestCommonPrefix(new String[]{"abcdd", "abddd", "abceee"});
        System.out.println(commonPrefix);
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0){
            return "";
        }
        String commonPrefix = strs[0];
        for(int i=0;i<strs.length-1;i++){
            String pre=strs[i];
            String post=strs[i+1];
            String tempCommonPrefix="";
            int length=pre.length()<post.length()?pre.length():post.length();
            for(int j=0;j<length;j++){
                if(pre.charAt(j)!=post.charAt(j)){
                    break;
                }else{
                    tempCommonPrefix+=pre.charAt(j);
                }
            }
            if(tempCommonPrefix.length()<commonPrefix.length()){
                commonPrefix=tempCommonPrefix;
            }
        }
        return commonPrefix;
    }
}
