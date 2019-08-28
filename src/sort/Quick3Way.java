package sort;

public class Quick3Way {
    private static void sort(Comparable[] a, int low, int high) {
        if (high <= low) return;
        int lt = low, i = low + 1, gt = high;
        Comparable key = a[low];
        while (i <= gt) {
            int cmp = a[i].compareTo(key);
            if (cmp < 0) swap(a, lt++, i++);
            else if (cmp > 0) swap(a, i, gt--);
            else i++;
        }
        //现在 a[ lo..lt - 1] <v = a[lt..gt] <a[gt + 1..hi] 成立
        sort(a, low, lt - 1);
        sort(a, gt + 1, high);
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
