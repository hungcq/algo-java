package session_2;

/**
 * Created by: HungCQ
 * Date: 20-May-20
 */
public class Array {
    private int size;
    private int[] arr;

    public Array() {
        arr = new int[0];
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
        if (index > size - 1) {
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
        if (arr.length == 0) {
            arr = new int[1];
            return;
        }
        if (size > arr.length) {
            int[] newArr = new int[arr.length * 2];
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
        if (index >= size - 1) {
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
        if (size <= arr.length / 4) {
            int[] newArr = new int[arr.length / 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }
}
