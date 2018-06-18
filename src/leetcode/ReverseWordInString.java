package leetcode;

public class ReverseWordInString {
    public static void main(String[] args) {
        System.out.println(new ReverseWordInString().reverseWords("   a   b "));;
    }
    public String reverseWords(String s) {
        if(s.trim().length()==0){
            return "";
        }
        char[] chars=s.trim().toCharArray();
        cleanSpace(chars);
        reverse(chars,0,chars.length-1);
        int start=0;
        int end=0;
        while(start<chars.length){
            while(end<chars.length&&chars[end]!=' ')++end;
            reverse(chars,start,end-1);
            while(end<chars.length&&chars[end]==' ')++end;
            start=end;
        }
        return new String(chars).trim();
    }

    private void cleanSpace(char[] a) {
        int i = 0, j = 0;
        int n=a.length;
        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

    }

    public void reverse(char[] chars,int start,int end){
        int i=start;
        int j=end;
        if(i>=j){
            return;
        }
        while(i<j){
            char temp=chars[i];
            chars[i]=chars[j];
            chars[j]=temp;
            ++i;
            --j;
        }
    }
}
