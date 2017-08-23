package leetcode;

/**
 * Created by Datartvinci on 2017/5/27.
 */
public class IntToRoma {
    public static void main(String[] args) {
        System.out.println(intToRoman(3999));
        System.out.println(intToRomans(3999));
        System.out.println(intToRoman(2));
        System.out.println(intToRoman(99));
    }

    public static String intToRomans(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
    // 阿拉伯数字转罗马数字：
    // 把所有小数字在前的组合也作为基本数字，再做一个对应的数值表就可以解决问题了。
    // I、V、X、   L、   C、     D、     M
    // 1．5、10、50、100、500、1000
    public static String intToRoman(int num) {
        int aNumber=num;
        if(aNumber < 1 || aNumber > 3999){
            return "-1";
        }
        //因为不做减法，所以把减法组合的数字先列出来
        int[] aArray = {1000,900,500,400,100,90,50,40,11,10,9,5,4,1};
        String[] rArray = {"M","CM","D","CD","C","XC","L","XL","XI","X","IX","V","IV","I"};
        String rNumber = "";
        for(int i=0; i<aArray.length; i++){
            while(aNumber >= aArray[i]){
                rNumber += rArray[i];
                aNumber -= aArray[i];
            }
        }
        return rNumber;
    }
}
