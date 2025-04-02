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
import com.github.javafaker.Faker;

/*
 *
 * Useful stuff
 *
 */

public class Utilities
{
    public static <T> void printArray(T[] a)
    {
        int len = a.length;
        System.out.printf("[");
        for (int i = 0; i < len; i++) {
            System.out.print(a[i]);
            if (i < len - 1) {
                System.out.printf(", ");
            }
        }
        System.out.printf("]");
    }

    public static void printIntArray(int[] a)
    {
        int len = a.length;
        System.out.printf("[");
        for (int i = 0; i < len; i++) {
            System.out.print(a[i]);
            if (i < len - 1) {
                System.out.printf(", ");
            }
        }
        System.out.printf("]");
    }

    public static int[] randomIntArray(int n)
    {
        Random rng = new Random(System.nanoTime());
        int len = Math.abs((rng.nextInt()) % n) + 1;
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = rng.nextInt() % 100;
        }
        return a;
    }

    public static Integer[] randomIArray(int n)
    {
        Random rng = new Random(System.nanoTime());
        int len = Math.abs((rng.nextInt()) % n) + 1;
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = rng.nextInt() % 100;
        }
        return a;
    }

    public static Integer[] randomIArrayFull(int len)
    {
        Random rng = new Random(System.nanoTime());
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = rng.nextInt() % 100000;
        }
        return a;
    }

    public static String[] randomStrArray(int n)
    {
        Faker f = new Faker();
        int len = Math.abs(new Random(System.nanoTime()).nextInt()) % 25 + 1;
        String[] strs = new String[len];
        for (int j = 0; j < len; j++) {
            strs[j] = f.name().firstName();
        }
        return strs;
    }
}
