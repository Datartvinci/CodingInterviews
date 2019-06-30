package sort;

public class Quick3Way {
    private static void sort(Comparable[] a, int lo, int hi) {
        //调用此方法的公有方法sort() 请见算法2 .5 if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) swap(a, lt++, i++);
            else if (cmp > 0) swap(a, i, gt--);
            else i++;
        }
        //现在 a[ lo..lt - 1] <v = a[lt..gt] <a[gt + 1..hi] 成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
