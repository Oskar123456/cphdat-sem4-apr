package cphdatadvprg;

import java.util.*;

/*
 *
 * Exercise Template
 *
 */

public class StepSorts
{
    public static <T> Queue<Integer[]> bubbleSort(T[] a, Comparator<T> cmpr, int[] n_cmprs)
    {
        Queue<Integer[]> swaps = new ArrayDeque<>();

        int len = a.length;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < len; i++) {
                ++n_cmprs[0];
                if (cmpr.compare(a[i - 1], a[i]) > 0) {
                    swap(a, i - 1, i, swaps);
                    swapped = true;
                }
            }
        }

        return swaps;
    }

    public static <T> Queue<Integer[]> heapSort(T[] a, Comparator<T> cmpr, int[] n_cmprs)
    {
        Queue<Integer[]> swaps = new ArrayDeque<>();

        int end = a.length;
        int start = a.length / 2;
        while (end > 1) {
            if (start > 0) {
                --start;
            } else {
                --end;
                swap(a, 0, end, swaps);
            }
            int root = start;
            while (root * 2 + 1 < end) {
                int child = root * 2 + 1;
                n_cmprs[0]++;
                if (child + 1 < end && cmpr.compare(a[child], a[child + 1]) <= 0) {
                    child = child + 1;
                }
                n_cmprs[0]++;
                if (cmpr.compare(a[root], a[child]) <= 0) {
                    swap(a, root, child, swaps);
                    root = child;
                } else {
                    break;
                }
            }
        }

        return swaps;
    }

    public static <T> Queue<Integer[]> quickSort(T[] a, Comparator<T> cmpr, int[] n_cmprs)
    {
        Queue<Integer[]> swaps = new ArrayDeque<>();
        quickSortInternal(a, 0, a.length - 1, cmpr, n_cmprs, swaps);
        return swaps;
    }

    private static <T> void quickSortInternal(T[] a, int lo, int hi, Comparator<T> cmpr, int[] n_cmprs, Queue<Integer[]> swaps)
    {
        if (lo >= hi || lo < 0) {
            return;
        }

        int mid = quickSortPartitionInternal(a, lo, hi, cmpr, n_cmprs, swaps);

        quickSortInternal(a, lo, mid - 1, cmpr, n_cmprs, swaps);
        quickSortInternal(a, mid + 1, hi, cmpr, n_cmprs, swaps);
    }

    private static <T> int quickSortPartitionInternal(T[] a, int lo, int hi, Comparator<T> cmpr, int[] n_cmprs, Queue<Integer[]> swaps)
    {
        T pivot = a[hi];
        int pivot_idx = lo;

        for (int i = lo; i < hi; i++) {
            ++n_cmprs[0];
            if (cmpr.compare(a[i], pivot) <= 0) {
                swap(a, pivot_idx, i, swaps);
                ++pivot_idx;
            }
        }

        swap(a, pivot_idx, hi, swaps);
        return pivot_idx;
    }

    public static <T> void swap(T[] a, int i, int j, Queue<Integer[]> q)
    {
        q.add(new Integer[] { i, j });
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
