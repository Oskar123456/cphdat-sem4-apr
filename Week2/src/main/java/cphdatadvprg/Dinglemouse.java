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

public class Dinglemouse
{
    public static boolean line(final char [][] grid)
    {
        Vec2i start = new Vec2i(-1, -1), dest = new Vec2i(-1, -1);
        int len = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'X') {
                    if (start.i < 0) {
                        start.i = i; start.j = j;
                    } else {
                        dest.i = i; dest.j = j;
                    }
                } else if ("+-|".indexOf(grid[i][j]) >= 0) {
                    ++len;
                }
            }
        }
        return lineRec(grid, new boolean[grid.length][grid[0].length], start, dest, new Vec2i(0, 0), len, new int[]{0}) ||
            lineRec(grid, new boolean[grid.length][grid[0].length], dest, start, new Vec2i(0, 0), len, new int[]{0});
    }

    public static boolean lineRec(final char[][] grid, boolean[][] vis, Vec2i pos, Vec2i dest, Vec2i dir, int len, int[] n)
    {
        if (!isInBounds(grid, pos)) {
            return false;
        }

        if (vis[pos.i][pos.j]) {
            return false;
        }

        if (pos.equals(dest)) {
            if (lineLen(grid, vis) == len) {
                if (n[0] == 0) {
                    ++n[0];
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        vis[pos.i][pos.j] = true;
        print(grid, vis, pos, dest, dir, len);

        boolean r = false;
        switch (grid[pos.i][pos.j]) {
            case '|':
                r = (dir.equals(Vec2i.north) || dir.equals(Vec2i.south)) && lineRec(grid, vis, pos.add(dir), dest, dir, len, n);
                break;
            case '-':
                r = (dir.equals(Vec2i.east) || dir.equals(Vec2i.west)) && lineRec(grid, vis, pos.add(dir), dest, dir, len, n);
                break;
            case 'X':
                r = lineRec(grid, vis, pos.add(Vec2i.north), dest, Vec2i.north, len, n)
                    || lineRec(grid, vis, pos.add(Vec2i.south), dest, Vec2i.south, len, n)
                    || lineRec(grid, vis, pos.add(Vec2i.east),  dest, Vec2i.east, len, n)
                    || lineRec(grid, vis, pos.add(Vec2i.west),  dest, Vec2i.west, len, n);
                break;
            case '+':
                // System.out.printf("trying %s --> %s%n", pos.toString(), pos.add(pos.right(dir)).toString());
                // System.out.printf("trying %s --> %s%n", pos.toString(), pos.add(pos.left(dir)).toString());
                r = lineRec(grid, vis, pos.add(pos.left(dir)), dest, pos.left(dir), len, n)
                    || lineRec(grid, vis, pos.add(pos.right(dir)),  dest, pos.right(dir), len, n);
                break;
            default:
                break;
        }

        if (r) {
            return n[0] == 1;
        } else {
            vis[pos.i][pos.j] = false;
            return false;
        }
    }

    public static int lineLen(final char[][] grid, boolean[][] vis)
    {
        int len = 0;
        for (int i = 0; i < vis.length; i++) {
            for (int j = 0; j < vis[0].length; j++) {
                if (vis[i][j] && grid[i][j] != 'X') {
                    ++len;
                }
            }
        }
        return len;
    }

    public static boolean isInBounds(final char[][] grid, Vec2i pos)
    {
        return pos.i >= 0 && pos.i < grid.length && pos.j >= 0 && pos.j < grid[0].length
            && "+-|X".indexOf(grid[pos.i][pos.j]) >= 0;
    }

    public static void print(final char[][] grid, boolean[][] vis, Vec2i pos, Vec2i dest, Vec2i dir, int len)
    {
        System.out.printf("cur_len / len : %d / %d\tpos: %s | dest: %s | dir: %s%n",
                lineLen(grid, vis), len, pos.toString(), dest.toString(), dir.toString());
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (vis[i][j]) {
                    System.out.printf("[%c]", grid[i][j]);
                } else {
                    System.out.printf(" %c ", grid[i][j]);
                }
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }
}
