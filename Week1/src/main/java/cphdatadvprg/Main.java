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
    // static Random rng = new Random(System.nanoTime());

    public static void main(String[] args)
    {
        // Comparator<Integer> cmpr = Comparator.naturalOrder();
        // Random rng = new Random();
        // for (int i = 0; i < 1000; i++) {
        //     int sample_size = Math.abs(rng.nextInt() % 1000) + 1;
        //     Integer[] a = Utilities.randomIArray(sample_size);
            // Utilities.printArray(a);
            // System.out.printf(" --> ");
            // Sorts.heapSort(a, cmpr);
            // for (int j = 0; j < a.length - 1; j++) {
            //     assert(cmpr.compare(a[j], a[j + 1]) <= 0);
            // }
            // // Utilities.printArray(a);
            // System.out.println();
        // }

        // javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());

        // int sample_size = 10;

        // MyHashMap<Person, Integer> person_map = new MyHashMap<>();
        // for (int i = 0; i < sample_size; i++) {
        //     Person p = new Person(Utilities.fastRandomName(), Utilities.randomPositiveInt(100));
        //     Integer val = Utilities.randomPositiveInt(100);
        //     person_map.put(p, val);
        //     Integer val_returned = person_map.get(p);
        //     assert(val.equals(val_returned));
        // }
        // System.out.println(person_map.toString());

        // benchmarkMyHashMap(10000, 25);

        // System.out.println(person_map.toString());

        // for (var ent : person_map.entrySet()) {
        //     System.out.printf("%s:%x%n", ent.getKey(), ent.getKey().hashCode());
        // }
    }

    private static void benchmarkMyHashMap(int increment, int n)
    {
        List<Integer[]> data_bucket_size = new ArrayList<>();
        List<Long[]> data_time = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.printf("i: %d, samples: %d%n", i, increment * i);
            MyHashMap<Person, Integer> person_map = new MyHashMap<>();
            long ns_pre = System.nanoTime();
            for (int j = 0; j < increment * i; j++) {
                person_map.put(new Person(Utilities.fastRandomName(), Utilities.randomPositiveInt(100)),
                            Utilities.randomPositiveInt(100));
            }
            long ns_post = System.nanoTime();
            data_bucket_size.add(new Integer[] { increment * i, person_map.longest_bucket });
            data_time.add(new Long[] { (long)increment * i, ns_post - ns_pre });
        }

        try {
            Files.createDirectories(Paths.get("data"));
            FileWriter output_writer;
            String output_text;

            output_text = "";
            for (int i = 0; i < data_time.size(); i++) {
                output_text += data_time.get(i)[0] + " ";
            }
            output_text += System.lineSeparator();
            for (int i = 0; i < data_time.size(); i++) {
                output_text += data_time.get(i)[1] + " ";
            }
            output_writer = new FileWriter("data/benchmarks_myhashmap_time.txt");
            output_writer.write(output_text);
            output_writer.close();

            output_text = "";
            for (int i = 0; i < data_bucket_size.size(); i++) {
                output_text += data_bucket_size.get(i)[0] + " ";
            }
            output_text += System.lineSeparator();
            for (int i = 0; i < data_bucket_size.size(); i++) {
                output_text += data_bucket_size.get(i)[1] + " ";
            }
            output_writer = new FileWriter("data/benchmarks_myhashmap_bucketsize.txt");
            output_writer.write(output_text);
            output_writer.close();

            System.out.printf("MyHashMap benchmark results output to folder: %s", Utilities.ls("data"));
        } catch (Exception e) {
            System.out.printf("In benchmarkMyHashMap(): %s%n", e.getMessage());
        }
    }

    private static void createAndShowGUI()
    {
        MainFrame frame = new MainFrame("HelloWorldSwing");
    }
}

class Person
{
    public String name;
    public Integer age;

    public Person()
    {
        Random rng = new Random(System.nanoTime());
        Faker f = new Faker();
        name = f.name().fullName();
        age = Math.abs(rng.nextInt()) % 100;
    }

    public Person(String name, Integer age) throws IllegalArgumentException
    {
        if (name == null || age == null) {
            throw new IllegalArgumentException("LOL");
        }
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Person p = (Person)obj;
        return p.name.equals(name) && p.age.equals(age);
    }

    @Override
    public int hashCode()
    {
        return name.hashCode() ^ age.hashCode();
    }

    @Override
    public String toString()
    {
        return "[" + name + ", " + age + "]";
    }
}
