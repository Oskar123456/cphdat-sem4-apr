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
        String       cake   = null;
        List<String> result = null;

        cake = String.join("\n", Arrays.asList(
        "........",
        "..o.....",
        "...o....",
        "........"
        ));

        result = Arrays.asList(
        "........\n"+
        "..o.....",
        "...o....\n"+
        "........");

        List<String> actual = new CakeCutter(cake).cut();

        System.out.printf("%s%n", cake);
        System.out.println();
        for (String s : actual) {
            System.out.printf("%s%n%n", s);
        }
        System.out.println();
        for (String s : result) {
            System.out.printf("%s%n%n", s);
        }
        System.out.println();
        System.out.printf("\t%b%n", result.equals(actual));

        System.out.println();

        CakeCutter cc = new CakeCutter(cake);
        boolean[][] cake_formatted = cc.makeCake();
        boolean[][] cake_cuts = new boolean[2][Math.max(cake_formatted[0].length - 1, cake_formatted.length - 1)];
        // cake_cuts[0][2] = true;;
        cake_cuts[1][1] = true;;
        System.out.printf("verify cake: %b%n", cc.verify(cake_formatted, cake_cuts));
    }
}





















