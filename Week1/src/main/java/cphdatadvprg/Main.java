package cphdatadvprg;

import java.util.*;
import com.github.javafaker.Faker;
import java.security.MessageDigest;

/*
 *
 * Exercise Template
 *
 */

public class Main
{
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

        int sample_size = 1000000;
        Random rng = new Random(System.nanoTime());

        MyHashMap<Person, Integer> person_map = new MyHashMap<>();
        for (int i = 0; i < sample_size; i++) {
            person_map.put(new Person("eou" + i, 77), Math.abs(rng.nextInt()) % 10000);
            // System.out.println(person_map.toString());
        }

        // System.out.println(person_map.toString());

        // for (var ent : person_map.entrySet()) {
        //     System.out.printf("%s:%x%n", ent.getKey(), ent.getKey().hashCode());
        // }
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

class MyHashMap<K, V>
{
    int init_cap = 8;
    int cap, size, insertions, collisions;
    Vector<Entry<K, V>> entries;

    public MyHashMap()
    {
        cap = init_cap;
        entries = new Vector<>(cap);
        entries.setSize(cap);
    }

    public int getLongestList()
    {
        int max = 0;
        for (var ent : entries) {
            int len = 0;
            while (ent != null) {
                ++len;
                ent = ent.next;
            }
            max = Math.max(max, len);
        }
        return max;
    }

    public void put(K k, V v)
    {
        ++size; ++insertions;
        float load_capacity = (float)size / cap;
        if (load_capacity > 0.75f) {
            System.out.printf("growing %d --> %d%n", cap, cap *= 2);
            int old_cap = cap; cap *= 2; size = 0;
            Vector<Entry<K, V>> old_list = entries;
            entries = new Vector<>(cap);
            entries.setSize(cap);
            for (int i = 0; i < cap; i++) {
                entries.add(null);
            }
            for (var ent : old_list) {
                while (ent != null) {
                    put(ent.key, ent.value);
                    ent = ent.next;
                }
            }
        }
        int idx = Math.abs(k.hashCode()) % cap;
        if (entries.get(idx) == null) {
            entries.set(idx, new Entry<K, V>(k, v));
        } else {
            ++collisions;
            Entry<K, V> ent = entries.get(idx);
            while (ent.next != null) {
                ent = ent.next;
            }
            ent.next = new Entry<K, V>(k, v);
        }
    }

    @Override
    public String toString()
    {
        String str = String.format("MyHashMap (size %d, cap %d, longest bucket: %d,  insertions: %d, collisions: %d [%d%%]) [\n",
                size, cap, getLongestList(), insertions, collisions, (int) (100 * (float)collisions / insertions));
        // for (var e : entries) {
        //     str += "\t[";
        //     while (e != null) {
        //         str += "key:" + e.key + " | value:" + e.value + " ";
        //         e = e.next;
        //     }
        //     str += "]\n";
        // }
        str += "]";
        return str;
    }

    class Entry<K, V>
    {
        public K key;
        public V value;
        public Entry<K, V> prev, next;
        public Entry(K k, V v) { key = k; value = v; }
    }
}
