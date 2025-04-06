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

public class Main
{
    static Faker name_gen = new Faker();
    static Random rng = new Random(System.nanoTime());

    public static void main(String[] args)
    {
        // String       cake   = null;
        // List<String> result = null;

        // cake = String.join("\n", Arrays.asList(
        // "........",
        // "..o.....",
        // "...o....",
        // "........"
        // ));

        // result = Arrays.asList(
        // "........\n"+
        // "..o.....",
        // "...o....\n"+
        // "........");

        // List<String> actual = new CakeCutter(cake).cut();

        // System.out.printf("%s%n", cake);
        // System.out.println();
        // for (String s : actual) {
        //     System.out.printf("%s%n%n", s);
        // }
        // System.out.println();
        // for (String s : result) {
        //     System.out.printf("%s%n%n", s);
        // }
        // System.out.println();
        // System.out.printf("\t%b%n", result.equals(actual));

        // System.out.println();

        // CakeCutter cc = new CakeCutter(cake);
        // boolean[][] cake_formatted = cc.makeCake();
        // boolean[][] cake_cuts = new boolean[2][Math.max(cake_formatted[0].length - 1, cake_formatted.length - 1)];
        // // cake_cuts[0][2] = true;;
        // cake_cuts[1][1] = true;;
        // System.out.printf("verify cake: %b%n", cc.verify(cake_formatted, cake_cuts));

        // /* Dinglemouse */

        // String s1 = "                      \n" +
        //             "   +-------+          \n" +
        //             "   |      +++---+     \n" +
        //             "X--+      +-+   X     ";

        // String s2 = "X-----|----X";

        // String s3 = "      +------+\n" +
        //             "      |      |\n" +
        //             "X-----+------+\n" +
        //             "      |       \n" +
        //             "      X       ";

        // String s4 = "X-----+\n" +
        //             "X     |\n" +
        //             "|     |\n" +
        //             "|     |\n" +
        //             "+-----+";

        // String s5 = " +++X\n" +
        //             " +++ \n" +
        //             "X+++ ";

        // String s6 =
        //     "X++++X\n" +
        //     "  ++  \n" +
        //     " ++++ \n" +
        //     " ++++ \n" +
        //     "X-++-X";

        // char[][] s1_chars = strToCharArray2D(s1);
        // char[][] s2_chars = strToCharArray2D(s2);
        // char[][] s3_chars = strToCharArray2D(s3);
        // char[][] s4_chars = strToCharArray2D(s4);
        // char[][] s5_chars = strToCharArray2D(s5);
        // char[][] s6_chars = strToCharArray2D(s6);

        // System.out.printf("Dinglemouse(s1): %b%n", Dinglemouse.line(s1_chars));
        // System.out.printf("Dinglemouse(s2): %b%n", Dinglemouse.line(s2_chars));
        // System.out.printf("Dinglemouse(s3): %b%n", Dinglemouse.line(s3_chars));
        // System.out.printf("Dinglemouse(s4): %b%n", Dinglemouse.line(s4_chars));
        // System.out.printf("Dinglemouse(s5): %b%n", Dinglemouse.line(s5_chars));
        // System.out.printf("Dinglemouse(s6): %b%n", Dinglemouse.line(s6_chars));

        /* Battleship field validator II */
        // int[][] field = {{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
        //                  {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
        //                  {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
        //                  {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                  {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        //                  {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        //                  {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        //                  {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
        //                  {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        //                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        // int[][] field = {{0,0,0,0,0,0,0,1,1,0},
        //     {0,0,0,0,0,0,0,0,0,0},
        //     {0,0,0,0,0,0,0,1,1,1},
        //     {0,0,0,1,0,0,0,1,0,0},
        //     {0,0,0,0,0,0,0,1,0,0},
        //     {0,0,0,0,0,1,0,1,0,0},
        //     {0,0,0,0,0,0,0,0,0,1},
        //     {0,0,0,0,0,0,1,0,0,1},
        //     {0,0,1,0,0,0,0,0,1,1},
        //     {0,0,1,0,0,0,1,1,1,0}};

        // BF battlefield = new BF(field);
        // boolean is_field_validated = battlefield.validate();
        // System.out.printf("%s%n\t%b%n", Tools.stringify(field), is_field_validated);

        // Stack<File> dirs = new Stack<>();
        // File root_dir = new File(".");
        // dirs.push(root_dir);
        // BTree<String> dir_tree = new BTree(root_dir);
        // while (dirs.size() > 0) {
        //     root_dir = dirs.pop();
        //     for (File child : root_dir.files()) {

        //     }
        // }
    }

    public static char[][] strToCharArray2D(String s)
    {
        String[] s_split = s.split("\n");
        char[][] chars2D = new char[s_split.length][s_split[0].length()];
        for (int i = 0; i < s_split.length; i++) {
            chars2D[i] = s_split[i].toCharArray();
        }
        return chars2D;
    }
}





















