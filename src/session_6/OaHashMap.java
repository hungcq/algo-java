package session_6;

public class OaHashMap {

    private static final double THRES_HOLD = 0.5;

    private final Node DELETED = new Node(0, new Object());

    private Node[] table;

    public void put(int key, Object value) {
        int index = search(key);
        if (index != -1) {
            table[index].value = value;
            return;
        }
        for (int i = 1; i <= table.length; i++) {
            int idx = hash(key, i);
            if (table[idx] == null || table[idx] == DELETED) {
                table[idx] = new Node(key, value);
                return;
            }
        }
    }

    public void delete(int key) {
        int index = search(key);
        if (index == -1) {
            return;
        }
        table[index] = DELETED;
    }

    private int hash(int key, int trial) {
        return (key + trial) % table.length;
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
