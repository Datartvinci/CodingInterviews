package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        Integer[] a = {5, 4, 3, 2, 1, 9, 8, 7, 6};
        System.out.print("before:");
        Arrays.asList(a).forEach(x -> System.out.print(x + " "));
        System.out.println();
        sort(a);
        System.out.print("after :");
        Arrays.asList(a).forEach(x -> System.out.print(x + " "));
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (high <= low) return;
        int j = partition2(a, low, high);  // 切分（请见“快速排序的切分”）
        // 将左半部分a[low .. j-1]排序
        sort(a, low, j - 1);
        // 将右半部分a[j+1 .. high]排序
        sort(a, j + 1, high);
    }


    // 将数组切分为a[lo..i-1], a[i], a[i+1..hi]
    private static int partition(Comparable[] a, int low, int high) {
        // 左右扫描指针
        int i = low, j = high + 1;
        // 切分元素
        Comparable key = a[low];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], key)) if (i == high) break;
            while (less(key, a[--j])) if (j == low) break;
            if (i >= j) break;
            swap(a, i, j);
        }
        // 将v = a[j]放入正确的位置
        swap(a, low, j);
        // a[lo..j-1] <= a[j] <= a[j+1..hi] 达成
        return j;
    }

    // 将数组切分为a[lo..i-1], a[i], a[i+1..hi]
    private static int partition2(Comparable[] a, int low, int high) {
        // 两个指针均向右扫描
        int i = low + 1;
        // 切分元素
        Comparable key = a[low];
        for (int j = i + 1; j <= high; j++) {
            if (less(a[j], key)) {
                swap(a, i, j);
                i = i + 1;
            }
        }
        swap(a, low, i);
        return i;
    }

    //链表快排
    private static class Node {
        int val;
        Node next;
    }

    private void quickSortList(Node head) {
        quickSortList(head, null);
    }

    private void quickSortList(Node head, Node tail) {
        if (head == null || head == tail) {
            return;
        }
        Node pivot = partitionList(head, tail);
        quickSortList(head, pivot);
        quickSortList(pivot.next, tail);
    }

    private static Node partitionList(Node head, Node tail) {
        if (head == null || head.next == null) {
            return head;
        }
        int key = head.val;
        Node i = head.next;
        for (Node j = i.next; j != tail; j = j.next) {
            if (j.val < key) {
                swap(i, j);
                i = i.next;
            }
        }
        swap(head, i);
        return i;
    }

    private static void swap(Node i, Node j) {
        int temp = i.val;
        i.val = j.val;
        j.val = temp;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}