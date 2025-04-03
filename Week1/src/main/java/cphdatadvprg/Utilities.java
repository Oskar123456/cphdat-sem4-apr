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
import java.io.FileWriter;
import java.io.File;
import java.nio.file.*;

/*
 *
 * Useful stuff
 *
 */

public class Utilities
{
    static Faker name_gen = new Faker();
    static Random rng = new Random(System.nanoTime());

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

    public static String ls(String dir_str)
    {
        String str = String.format("%s%n", dir_str);
        File dir = new File(dir_str);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                str += String.format("\t%s%n", child.getName());
            }
        }
        return str;
    }

    public static String fastRandomName()
    {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String name = "";
        int n = (rng.nextInt() % 20 + 20) % 20 + 5;
        for (int i = 0; i < n; i++) {
            name += chars[(rng.nextInt() % 26 + 26) % 26];
        }
        return name;
    }

    public static int randomPositiveInt(int mod)
    {
        return (rng.nextInt() % mod + mod) % mod;
    }
}
