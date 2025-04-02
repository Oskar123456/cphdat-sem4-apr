package cphdatadvprg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Float;
import java.awt.Graphics2D.*;

/*
 *
 * Exercise Template
 *
 */

public class StepSorts
{
    public static <T> void heapSortGenericCmpr(T[] a, Comparator<T> cmpr)
    {
        int len = a.length;
        int start = len / 2;
        int end = len;
        while (end > 1) {
            heapifyGenericCmpr(a, end, cmpr);
            --end;
            swapGeneric(a, 0, end);
        }
    }

    public static <T extends Comparable<T>> void heapSortGeneric(T[] a)
    {
        int len = a.length;
        int start = len / 2;
        int end = len;
        while (end > 1) {
            heapifyGeneric(a, end);
            --end;
            swapGeneric(a, 0, end);
        }
    }

    public static <T> void swapGeneric(T[] a, int i, int j)
    {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void swap(Integer[] a, int i, int j)
    {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void heapSort(Integer[] a)
    {
        int end = a.length;
        int start = end / 2;
        while (end > 1) {
            if (start > 0) {
                --start;
            } else{
                --end;
                swap(a, end, 0);
            }
            int root = start;
            while (root * 2 + 1 < end) {
                int child = root * 2 + 1;
                if (child + 1 < end && a[child] > a[child + 1]) {
                    ++child;
                }
                if (a[root] > a[child]) {
                    swap(a, root, child);
                    root = child;
                } else {
                    break;
                }
            }
        }
    }

    public static void heapSort2(Integer[] a)
    {
        int len = a.length;
        int start = len / 2;
        int end = len;
        while (end > 1) {
            heapify(a, end);
            --end;
            swap(a, 0, end);
        }
    }

    public static boolean verifySorted(Integer[] a)
    {
        if (a.length < 2) {
            return true;
        }
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyHeap(Integer[] a)
    {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int l = i * 2 + 1;
            int r = i * 2 + 2;
            if (l < len && a[i] >= a[l]) {
                System.out.printf("not heap: a[%d] = %d <  a[%d] = %d is false%n", i, a[i], l, a[l]);
                return false;
            }
            if (r < len && a[i] >= a[r]) {
                System.out.printf("not heap: a[%d] = %d <  a[%d] = %d is false%n", i, a[i], r, a[r]);
                return false;
            }
        }
        return true;
    }

    public static <T> void heapifyGenericCmpr(T[] a, int end, Comparator<T> cmpr)
    {
        int start = end / 2;
        while (start-- > 0) {
            int root = start;
            while (root * 2 + 1 < end) {
                int child = root * 2 + 1;
                if (child + 1 < end && cmpr.compare(a[child], a[child + 1]) > 0) {
                    ++child;
                }
                if (cmpr.compare(a[root], a[child]) > 0) {
                    swapGeneric(a, root, child);
                    root = child;
                } else {
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void heapifyGeneric(T[] a, int end)
    {
        int start = end / 2;
        while (start-- > 0) {
            int root = start;
            while (root * 2 + 1 < end) {
                int child = root * 2 + 1;
                if (child + 1 < end && a[child].compareTo(a[child + 1]) > 0) {
                    ++child;
                }
                if (a[root].compareTo(a[child]) > 0) {
                    swapGeneric(a, root, child);
                    root = child;
                } else {
                    break;
                }
            }
        }
    }

    public static void heapify(Integer[] a, int end)
    {
        int start = end / 2;
        while (start-- > 0) {
            int root = start;
            while (root * 2 + 1 < end) {
                int child = root * 2 + 1;
                if (child + 1 < end && a[child] > a[child + 1]) {
                    ++child;
                }
                if (a[root] > a[child]) {
                    swap(a, root, child);
                    root = child;
                } else {
                    break;
                }
            }
        }
    }
}
