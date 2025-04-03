package cphdatadvprg;

import java.util.*;
import java.security.MessageDigest;

/*
 *
 * Exercise Template
 *
 */

class MyHashMap<K, V>
{
    int init_cap = 8;
    int cap, size, insertions, collisions, longest_bucket;
    Entry[] entries;

    public MyHashMap()
    {
        cap = init_cap;
        entries = new Entry[cap];
    }

    public void put(K k, V v)
    {
        ++size; ++insertions;
        float load_capacity = (float)size / cap;
        if (load_capacity > 0.75f) {
            int old_cap = cap;
            cap *= 2;
            size = 0;
            longest_bucket = 0;
            Entry[] old_list = entries;
            entries = new Entry[cap];
            for (Entry<K, V> ent : old_list) {
                while (ent != null) {
                    put(ent.key, ent.value);
                    ent = ent.next;
                }
            }
        }
        int idx = hashIdx(k);
        if (entries[idx] == null) {
            entries[idx] = new Entry<K, V>(k, v);
            longest_bucket = Math.max(longest_bucket, 1);
        } else {
            ++collisions;
            int bucket_len = 2;
            Entry ent = entries[idx];
            while (ent.next != null) {
                ent = ent.next;
                ++bucket_len;
            }
            longest_bucket = Math.max(longest_bucket, bucket_len);
            ent.next = new Entry<K, V>(k, v);
        }
    }

    public V get(K k)
    {
        int idx = hashIdx(k);
        Entry ent = entries[idx];
        while (ent != null && !ent.key.equals(k) && ent.next != null) {
            ent = ent.next;
        }
        return (ent == null) ? null : (V)ent.value;
    }

    public void clear()
    {
        insertions = 0;
        collisions = 0;
        size = 0;
        longest_bucket = 0;
        cap = init_cap;
        entries = new Entry[cap];
    }

    @Override
    public String toString()
    {
        String str = String.format("MyHashMap (size %d, cap %d, longest bucket: %d,  insertions: %d, collisions: %d [%d%%]) [",
                size, cap, longest_bucket, insertions, collisions, (int) (100 * (float)collisions / insertions));
        if (cap <= 32) {
            for (var e : entries) {
                str += System.lineSeparator() + "\t[";
                while (e != null) {
                    str += "[key:" + e.key + "|val:" + e.value + "]";
                    if (e.next != null) {
                        str += " <--> ";
                    }
                    e = e.next;
                }
                str += "]";
            }
        }
        str += "]";
        return str;
    }

    private int hashIdx(K k)
    {
        return Math.abs(k.hashCode()) % cap;
    }

    class Entry<K, V>
    {
        public K key;
        public V value;
        public Entry<K, V> prev, next;
        public Entry(K k, V v) { key = k; value = v; }
    }
}

