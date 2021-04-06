package array_linked_list;

/**
 * Created by: HungCQ
 * Date: 20-May-20
 */
public class Array {
    private int size;
    private int[] arr;

    private static final int INITIAL_CAPACITY = 8;
    private static final int CHANGE_SIZE_FACTOR = 2;
    private static final double REDUCE_SIZE_THRESHOLD = 0.25;

    public Array() {
        arr = new int[INITIAL_CAPACITY];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int itemAt(int index) {
        if (index > size - 1 || index < 0) {
            return -1;
        }
        return arr[index];
    }

    public void append(int item) {
        size++;
        checkAndIncreaseSize();
        arr[size - 1] = item;
    }

    public boolean insert(int item, int index) {
        if (index < 0) {
            return false;
        }
        if (index == size) {
            append(item);
            return true;
        }
        if (index > size - 1) {
            return false;
        }
        size++;
        checkAndIncreaseSize();
        for (int i = size - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = item;
        return true;
    }

    private void checkAndIncreaseSize() {
        if (size > arr.length) {
            int[] newArr = new int[arr.length * CHANGE_SIZE_FACTOR];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }

    public int pop() {
        int value = arr[size - 1];
        arr[size - 1] = 0;
        size--;
        checkAndReduceSize();
        return value;
    }

    public int removeAt(int index) {
        if (index >= size - 1 || index < 0) {
            return -1;
        }
        int value = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = 0;
        size--;
        checkAndReduceSize();
        return value;
    }

    private void checkAndReduceSize() {
        if (size <= arr.length * REDUCE_SIZE_THRESHOLD) {
            int[] newArr = new int[arr.length / CHANGE_SIZE_FACTOR];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }
}
