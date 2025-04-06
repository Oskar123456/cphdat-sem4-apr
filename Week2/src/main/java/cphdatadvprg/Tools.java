package cphdatadvprg;

import java.util.*;
import com.github.javafaker.Faker;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.*;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.*;

/*
 *
 * Exercise Template
 *
 */

public class Tools
{
    static final char tree_branch = '─';
    static final char tree_branch_mid = '├';
    static final char tree_branch_end = '└';

    public static <T extends Comparable<T>> String stringify(BTree<T> tree)
    {
        StringBuilder out = new StringBuilder();
        tree_stringify_rec(tree, out, 0, new Vector<>());
        return out.toString();
    }

    static <T extends Comparable<T>> void tree_stringify_rec(BTree<T> tree, StringBuilder out,
            int depth, Vector<Boolean> indicator_array)
    {
        if (tree == null) {
            return;
        }

        indicator_array.setSize(depth + 1);

        if (tree.l != null) {
            indicator_array.set(depth, true);
        }

        if (tree.p != null && tree.p.l == tree) {
            // System.out.printf("%s %s%n", tree.p.value.toString(), tree.p.r.value.toString());
            System.out.printf("Setting indicator_array[%d] to false at d = %d [%s]%n", depth - 1, depth, tree.value.toString());
            indicator_array.set(depth - 1, false);
        }

        System.out.printf("[");
        for (var b : indicator_array) {
            System.out.printf("%d ", (b != null && b) ? 1 : 0);
        }
        System.out.printf("]%n");

        String to_str = (tree.value != null) ? tree.value.toString() : "null";
        for (int i = 0; i < depth; i++) {
            Boolean b = false;
            if (i > 0) {
                b = indicator_array.get(i - 1);
            }
            if (b == null || !b) {
                out.append("   ");
            } else {
                out.append("|  ");
            }
        }

        if (tree.p == null) {
        } else if (tree.p.r == tree) {
            if (tree.p.l == null) {
                out.append(String.format("%c%c%c ", tree_branch_end, tree_branch, tree_branch));
            } else {
                out.append(String.format("%c%c%c ", tree_branch_mid, tree_branch, tree_branch));
            }
        } else {
            out.append(String.format("%c%c%c ", tree_branch_end, tree_branch, tree_branch));
        }

        out.append(String.format("%s%n", to_str));

        tree_stringify_rec(tree.r, out, depth + 1, indicator_array);
        tree_stringify_rec(tree.l, out, depth + 1, indicator_array);
    }

    public static String stringify(boolean[] a)
    {
        String str = "[";
        for (int i = 0; i < a.length; i++) {
            str += String.format("%2d", a[i] ? 1 : 0);
        }
        str += "]";
        return str;
    }
    public static String stringify(int[] a)
    {
        int len = 2;
        for (int i = 0; i < a.length; i++) {
            int n = 0, val = Math.abs(a[i]);
            while (val > 0) {
                val /= 10;
                ++n;
            }
            len = Math.max(len, n + (a[i] < 0 ? 2 : 1));
        }

        String str = "[";
        for (int i = 0; i < a.length; i++) {
            String n = String.format("%d", a[i]);
            n = " ".repeat(len - n.length()) + n;
            str += n;
        }
        str += "]";
        return str;
    }

    public static String stringify(int[][] a)
    {
        int len = 2;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                int n = 0, val = Math.abs(a[i][j]);
                while (val > 0) {
                    val /= 10;
                    ++n;
                }
                len = Math.max(len, n + (a[i][j] < 0 ? 2 : 1));
            }
        }

        String str = "[";
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                str += "[";
            } else {
                str += "  ";
            }
            for (int j = 0; j < a[0].length; j++) {
                String n = String.format("%d", a[i][j]);
                n = " ".repeat(len - n.length()) + n;
                str += n;
            }
            str += "]";
            if (i < a.length - 1) {
                str += System.lineSeparator();
            }
        }
        str += "]";
        return str;
    }
}
