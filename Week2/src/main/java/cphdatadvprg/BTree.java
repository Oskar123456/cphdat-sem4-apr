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

public class BTree<T extends Comparable<T>>
{
    public T value;
    public int size;
    public BTree<T> p, l, r;
    public BTree(T t) { this.value = t; }

    public BTree() {}

    public void insert(T t)
    {
        ++size;
        if (value.compareTo(t) <= 0) {
            if (r == null) {
                r = new BTree(t);
                r.p = this;
            } else {
                r.insert(t);
            }
        } else {
            if (l == null) {
                l = new BTree(t);
                l.p = this;
            } else {
                l.insert(t);
            }
        }
    }
}
