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
 * Battleship field validator II
 *
 */

public class BF
{

    int[] ships = new int[]{ 4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };
    int ships_sum;
    boolean[] ships_used = new boolean[ships.length];
    boolean[][] print_indicator;
    int[][] field;
    int w, h;

    public BF(int[][] field)
    {
        this.field = field;
        w = field[0].length;
        h = field.length;
        for (int i = 0; i < ships.length; i++) {
            ships_sum += ships[i];
        }
        print_indicator = new boolean[h][w];
    }

    public boolean validate()
    {
        int acc = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                acc += field[i][j];
            }
        }
        if (acc != ships_sum) {
            return false;
        }
        return validate(0);
    }

    boolean validate(int ship)
    {
        System.out.printf("[");
        for (int i = 0; i < ships.length; i++) {
            System.out.printf("%2d", ships[i]);
        }
        System.out.printf("]%n");
        // System.out.println(Tools.stringify(ships_used));
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!print_indicator[i][j]) {
                    System.out.printf("%2d", field[i][j]);
                } else {
                    System.out.printf(" X");
                }
            }
            System.out.printf("%n");
        }
        // System.out.println(Tools.stringify(field));
        if (ship == ships.length) {
            return valid();
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (field[i][j] == 0) {
                    continue;
                }
                int len = ships[ship];
                if (i + len - 1 < h) {
                    boolean fits = true;
                    for (int k = 0; k < len; k++) {
                        if (field[i + k][j] == 0) {
                            fits = false;
                            break;
                        }
                    }
                    if (fits) {
                        for (int k = 0; k < len; k++) {
                            field[i + k][j] = 0;
                            print_indicator[i + k][j] = true;
                        }
                        ships_used[ship] = true;
                        if (validate(ship + 1)) {
                            return true;
                        }
                        ships_used[ship] = false;
                        for (int k = 0; k < len; k++) {
                            field[i + k][j] = 1;
                            print_indicator[i + k][j] = false;
                        }
                    }
                }
                if (j + len - 1 < w) {
                    boolean fits = true;
                    for (int k = 0; k < len; k++) {
                        if (field[i][j + k] == 0) {
                            fits = false;
                            break;
                        }
                    }
                    if (fits) {
                        for (int k = 0; k < len; k++) {
                            field[i][j + k] = 0;
                            print_indicator[i][j + k] = true;
                        }
                        ships_used[ship] = true;
                        if (validate(ship + 1)) {
                            return true;
                        }
                        ships_used[ship] = false;
                        for (int k = 0; k < len; k++) {
                            field[i][j + k] = 1;
                            print_indicator[i][j + k] = false;
                        }
                    }
                }
            }
        }
        System.out.printf("no space for another %d%n", ships[ship]);
        return false;
    }

    boolean isInBounds(Vec2i pos)
    {
        return pos.i >= 0 && pos.i < field.length && pos.j >= 0 && pos.j <= field[0].length;
    }

    boolean valid()
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
