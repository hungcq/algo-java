package session_6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class MyHashSet {

    private static final int TABLE_SIZE = 10000;

    public LinkedList<Integer>[] table;

    /** Initialize your data structure here. */
    public MyHashSet() {
        table = new LinkedList[TABLE_SIZE];
    }

    public void add(int key) {
        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }
        for (int item : table[hash]) {
            if (item == key) {
                return;
            }
        }
        table[hash].push(key);
    }

    public void remove(int key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return;
        }
        Iterator<Integer> iterator = table[hash].iterator();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
            if (item == key) {
                iterator.remove();
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return false;
        }
        for (int item : table[hash]) {
            if (item == key) {
                return true;
            }
        }
        return false;
    }

    private int hash(int key) {
        return key % TABLE_SIZE;
    }
}
