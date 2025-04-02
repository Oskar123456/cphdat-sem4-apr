package cphdatadvprg;

import java.util.*;

/*
 *
 * Exercise Template
 *
 */

public class Sorts
{
    public static <T> void heapSort(T[] a, Comparator<T> cmpr)
    {
        int start = a.length / 2;
        int end = a.length;
        while (start > 0) {
            heapify(a, cmpr, --start, end);
        }
        while (end > 1) {
            heapify(a, cmpr, 0, end--);
            swap(a, 0, end);
        }
    }

    public static <T> void heapify(T[] a, Comparator<T> cmpr, int lo, int hi)
    {
        int root = lo;
        while (root * 2 + 1 < hi) {
            int child = root * 2 + 1;
            if (child + 1 < hi && cmpr.compare(a[child], a[child + 1]) <= 0) {
                child = child + 1;
            }
            if (cmpr.compare(a[root], a[child]) <= 0) {
                swap(a, root, child);
                root = child;
            } else {
                break;
            }
        }
    }

    public static <T> void swap(T[] a, int i, int j)
    {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

