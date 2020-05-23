package session_6;

import java.util.Iterator;
import java.util.LinkedList;

class MyHashMap {

    private static final int TABLE_SIZE = 10000;

    public LinkedList<Entry>[] table;

    /** Initialize your data structure here. */
    public MyHashMap() {
        table = new LinkedList[TABLE_SIZE];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }
        for (Entry entry : table[hash]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        Entry entry = new Entry();
        entry.key = key;
        entry.value = value;
        table[hash].push(entry);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return -1;
        }
        for (Entry entry : table[hash]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return;
        }
        Iterator<Entry> iterator = table[hash].iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.key == key) {
                iterator.remove();
            }
        }
    }

    private int hash(int key) {
        return key % TABLE_SIZE;
    }


    class Entry {
        int key;
        int value;
    }
}