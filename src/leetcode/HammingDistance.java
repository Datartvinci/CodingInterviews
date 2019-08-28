package leetcode;

public class HammingDistance {
    public static void main(String[] args) {
        System.out.println(new HammingDistance().hammingDistance(1, 4));
    }

    public int hammingDistance(int x, int y) {
        String b1 = Integer.toBinaryString(x);
        String b2 = Integer.toBinaryString(y);
        int n1 = b1.length() - 1;
        int n2 = b2.length() - 1;
        int sum = 0;
        while (n1 >= 0 && n2 >= 0) {
            if (b1.charAt(n1--) != b2.charAt(n2--)) sum++;
        }
        while (n1 >= 0) {
            if (b1.charAt(n1--) == '1') sum++;
        }
        while (n2 >= 0) {
            if (b2.charAt(n2--) == '1') sum++;
        }
        return sum;
    }

    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
