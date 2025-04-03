package cphdatadvprg;

import java.util.*;
import com.github.javafaker.Faker;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.*;

/*
 *
 * Exercise Template
 *
 */

public class CakeCutter
{
    String cake;

    public CakeCutter(String cake)
    {
        this.cake = cake;
    }

    public List<String> cut()
    {
        return new ArrayList<>();
    }

    public boolean[][] makeCake()
    {
        int w = cake.indexOf("\n");
        int h = cake.length() - cake.replaceAll("\n", "").length() + 1;
        System.out.printf("%s%n\t%d %d%n", cake, w, h);
        boolean[][] cake_formatted = new boolean[h][w];
        for (int i = 0; i < cake.length(); i++) {
            if (cake.charAt(i) == 'o') {
                cake_formatted[i / (w + 1)][i % (w + 1)] = true;
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.printf("%d ", cake_formatted[i][j] ? 1 : 0);
            }
            System.out.printf("%n");
        }
        return cake_formatted;
    }

    public void search(boolean[][] cake, boolean[][] cuts)
    {
    }

    public boolean verify(boolean[][] cake, boolean[][] cuts)
    {
        int w = cake[0].length;
        int h = cake.length;

        int i_start = 0, j_start = 0;
        for (int i = 0; i < h; i++) {
            if (i == h - 1 || cuts[1][i]) {
                for (int j = 0; j < w; j++) {
                    if (j == w - 1 || cuts[0][j]) {
                        System.out.printf("checking bb [%d %d] [%d %d]%n", i_start, j_start, i, j);
                        int n_raisins = 0;
                        for (int m = i_start; m <= i; m++) {
                            for (int n = j_start; n <= j; n++) {
                                if (cake[m][n]) {
                                    n_raisins++;
                                }
                            }
                        }
                        if (n_raisins != 1) {
                            return false;
                        }
                        j_start = j + 1;
                    }
                }
                i_start = i + 1;
                j_start = 0;
            }
        }

        return true;
    }
}
