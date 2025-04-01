package searchandsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.lang.System;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class MyExamples
{
    static Random rng = new Random(System.nanoTime());

    public static void doTest(int n)
    {
        long c;
        long ns, ns_end, ns_const, ns_log, ns_lin, ns_square;

        List<Long[]> const_vals = new ArrayList<>();
        List<Long[]> log_vals = new ArrayList<>();
        List<Long[]> lin_vals = new ArrayList<>();
        List<Long[]> square_vals = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            long sample_size = 100 * i;

            ns = System.nanoTime();
            c = constant(sample_size);
            ns_end = System.nanoTime();
            ns_const = ns_end - ns;

            const_vals.add(new Long[] { sample_size, ns_const });

            ns = System.nanoTime();
            c = logarithmic(sample_size);
            ns_end = System.nanoTime();
            ns_log = ns_end - ns;

            log_vals.add(new Long[] { sample_size, ns_log });

            ns = System.nanoTime();
            c = linear(sample_size);
            ns_end = System.nanoTime();
            ns_lin = ns_end - ns;

            lin_vals.add(new Long[] { sample_size, ns_lin });

            ns = System.nanoTime();
            c = square(sample_size);
            ns_end = System.nanoTime();
            ns_square = ns_end - ns;

            square_vals.add(new Long[] { sample_size, ns_square });
        }

        String str_const = new String(), str_log = new String(), str_lin = new String(), str_square = new String();

        for (var vals : const_vals) {
            str_const += String.format("%d ", vals[0]);
        }
        str_const += String.format("%n");
        for (var vals : const_vals) {
            str_const += String.format("%d ", vals[1]);
        }

        for (var vals : log_vals) {
            str_log += String.format("%d ", vals[0]);
        }
        str_log += String.format("%n");
        for (var vals : log_vals) {
            str_log += String.format("%d ", vals[1]);
        }

        for (var vals : lin_vals) {
            str_lin += String.format("%d ", vals[0]);
        }
        str_lin += String.format("%n");
        for (var vals : lin_vals) {
            str_lin += String.format("%d ", vals[1]);
        }

        for (var vals : square_vals) {
            str_square += String.format("%d ", vals[0]);
        }
        str_square += String.format("%n");
        for (var vals : square_vals) {
            str_square += String.format("%d ", vals[1]);
        }

        try {
            FileWriter fw_const = new FileWriter("data/const_out.txt", false);
            FileWriter fw_log = new FileWriter("data/log_out.txt", false);
            FileWriter fw_lin = new FileWriter("data/lin_out.txt", false);
            FileWriter fw_square = new FileWriter("data/square_out.txt", false);

            BufferedWriter bw;

            bw = new BufferedWriter(fw_const);
            bw.write(str_const);
            bw.close();
            bw = new BufferedWriter(fw_log);
            bw.write(str_log);
            bw.close();
            bw = new BufferedWriter(fw_lin);
            bw.write(str_lin);
            bw.close();
            bw = new BufferedWriter(fw_square);
            bw.write(str_square);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static long constant(long n)
    {
        rng = new Random(System.nanoTime());
        return n * (rng.nextInt() % 10000);
    }

    public static long logarithmic(long n)
    {
        rng = new Random(System.nanoTime());
        long sum = 0;
        for (long i = n; i > 0; i /= 2) {
            sum += rng.nextInt() % 10000;
        }
        return sum;
    }

    public static long linear(long n)
    {
        rng = new Random(System.nanoTime());
        long sum = 0;
        for (long i = 0; i < n; ++i) {
            sum += rng.nextInt() % 10000;
        }
        return sum;
    }

    public static long square(long n)
    {
        rng = new Random(System.nanoTime());
        long sum = 0;
        for (long i = 0; i < n * n; ++i) {
            sum += rng.nextInt() % 10000;
        }
        return sum;
    }
}
