package hash_map_chaining;

import java.util.Iterator;
import java.util.LinkedList;

class HashMapChaining {

    public static void main(String[] args) {
        HashMapChaining map = new HashMapChaining();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(9, 9);
        System.out.println(map.getCapacity());
        map.put(6, 6);
        System.out.println(map.getCapacity());
        map.put(7, 7);
        map.put(8, 8);
        map.put(17, 17);
    }

    private static final int INCREASE_FACTOR = 2;
    private static final double THRESHOLD = 0.75;

    public static final int INITIAL_CAPACITY = 8;

    public LinkedList<Entry>[] table;
    public int size;

    public HashMapChaining() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    public int getCapacity() {
        return table.length;
    }

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

        size++;

        checkAndIncreaseSize();
    }

    private void checkAndIncreaseSize() {
        if (size < THRESHOLD * table.length) {
            return;
        }
        LinkedList<Entry>[] newTable = new LinkedList[table.length * INCREASE_FACTOR];
        for (LinkedList<Entry> linkedList : table) {
            if (linkedList == null) {
                continue;
            }
            for (Entry entry : linkedList) {
                int hash = hash(entry.key, newTable.length);
                if (newTable[hash] == null) {
                    newTable[hash] = new LinkedList<>();
                }
                newTable[hash].add(entry);
            }
        }
        table = newTable;
    }

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
        return key % table.length;
    }

    private int hash(int key, int length) {
        return key % length;
    }


    class Entry {
        int key;
        int value;
    }
}