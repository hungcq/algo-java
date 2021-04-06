package sort;

public class Sorts {
    // stable, comparison
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean modified = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    modified = true;
                }
            }
            if (!modified) {
                return;
            }
        }
    }

    // unstable when use swap, stable when pushing other elements
    // comparison
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    // stable, comparison
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j] > value; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = value;
        }
    }

    // stable, comparison
    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    private static void merge(int[] array, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        int index = 0;
        int[] tmp = new int[end - start + 1];
        while (i <= middle && j <= end) {
            if (array[i] < array[j]) {
                tmp[index] = array[i];
                i++;
            } else {
                tmp[index] = array[j];
                j++;
            }
            index++;
        }
        while (i <= middle) {
            tmp[index] = array[i];
            i++;
            index++;
        }
        while (j <= end) {
            tmp[index] = array[j];
            j++;
            index++;
        }
        i = start;
        for (int num : tmp) {
            array[i] = num;
            i++;
        }
    }

    // unstable, comparison
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition(array, start, end);
        quickSort(array, start, index - 1);
        quickSort(array, index + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start;
        int j = start;
        while (i < end) {
            if (array[i] < pivot) {
                swap(array, i, j);
                j++;
            }
            i++;
        }
        swap(array, j, end);
        return j;
    }

    // unstable, comparison
    public static void heapSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int lastNonLeafNodeIndex = array.length / 2 - 1;
        while (lastNonLeafNodeIndex >= 0) {
            siftDown(array, lastNonLeafNodeIndex, array.length - 1);
            lastNonLeafNodeIndex--;
        }
        int end = array.length - 1;
        while (end > 0) {
            swap(array, 0, end);
            end -= 1;
            siftDown(array, 0, end);
        }
    }

    private static void siftDown(int[] array, int position, int end) {
        while (true) {
            int tmp = position;
            if (position * 2 + 2 <= end && array[position * 2 + 2] > array[tmp]) {
                tmp = position * 2 + 2;
            }
            if (position * 2 + 1 <= end && array[position * 2 + 1] > array[tmp]) {
                tmp = position * 2 + 1;
            }
            if (tmp == position) {
                break;
            }
            swap(array, tmp, position);
            position = tmp;
        }
    }

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
