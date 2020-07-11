package gd_mindx.session_6;

public class OaHashMap {

    public static void main(String[] args) {
        OaHashMap oaHashMap = new OaHashMap();
        oaHashMap.put(1, 1);
        oaHashMap.put(2, 2);
        oaHashMap.put(3, 3);
        oaHashMap.put(4, 4);
        oaHashMap.delete(1);
        oaHashMap.put(5, 5);
        oaHashMap.put(6, 6);
        oaHashMap.delete(4);
        oaHashMap.put(7, 7);
        oaHashMap.put(8, 8);
        oaHashMap.delete(7);
        oaHashMap.delete(8);
        oaHashMap.delete(2);
        oaHashMap.delete(3);
        oaHashMap.delete(5);
    }

    private static final double THRES_HOLD = 0.5;
    private static final double REDUCE_SIZE_THRES_HOLD = 0.25;
    private static final int INITIAL_CAPACITY = 8;

    private final Node DELETED = new Node(0, new Object());

    private Node[] table;
    private int size;

    public OaHashMap() {
        table = new Node[INITIAL_CAPACITY];
    }

    public void put(int key, Object value) {
        add(key, value);
        size++;
        checkAndIncreaseCapacity();
    }

    private void checkAndIncreaseCapacity() {
        if (size <= THRES_HOLD * table.length) {
            return;
        }
        Node[] oldTable = table;
        table = new Node[table.length * 2];
        for (Node node : oldTable) {
            if (node == null || node == DELETED) {
                continue;
            }
            add(node.key, node.value);
        }
    }

    private void add(int key, Object value) {
        int index = search(key);
        if (index != -1) {
            table[index].value = value;
            return;
        }
        for (int i = 1; i <= table.length; i++) {
            int idx = hash(key, i);
            if (table[idx] == null || table[idx] == DELETED) {
                table[idx] = new Node(key, value);
                break;
            }
        }
    }

    public void delete(int key) {
        int index = search(key);
        if (index == -1) {
            return;
        }
        table[index] = DELETED;
        size--;
        checkAndReduceCapacity();
    }

    private void checkAndReduceCapacity() {
        if (table.length <= INITIAL_CAPACITY || size >= table.length * REDUCE_SIZE_THRES_HOLD) {
            return;
        }
        Node[] oldTable = table;
        table = new Node[table.length / 2];
        for (Node node : oldTable) {
            if (node != null && node != DELETED) {
                add(node.key, node.value);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Object get(int key) {
        int index = search(key);
        if (index != -1) {
            return table[index].value;
        }
        return null;
    }

    private int hash(int key, int trial) {
        return (hash1(key) + trial * hash2(key)) % table.length;
    }

    private int hash1(int key) {
        return key % table.length;
    }

    private int hash2(int key) {
        if (key % 2 == 0) {
            key += 1;
        }
        return key % table.length;
    }

    private int search(int key) {
        for (int i = 1; i <= table.length; i++) {
            int index = hash(key, i);
            if (table[index] == DELETED) {
                continue;
            }
            if (table[index] != null && table[index].key == key) {
                return index;
            } else {
                return -1;
            }
        }
        return -1;
    }

    private class Node {
        int key;
        Object value;

        Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
