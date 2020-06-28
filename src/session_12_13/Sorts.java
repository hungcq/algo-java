package session_12_13;

public class Sorts {
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

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

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

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private static void siftDown(int[] array, int position, int end) {
        int parent = position;
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        if (child1 > end) {
            return;
        }
        if (child2 > end) {
            child2 = child1;
        }
        int parentValue = array[parent];
        int child1Value = array[child1];
        int child2Value = array[child2];
        while (parentValue < child1Value || parentValue < child2Value) {
            if (child1Value > child2Value) {
                swap(array, parent, child1);
                parent = child1;
            } else {
                swap(array, parent, child2);
                parent = child2;
            }
            child1 = parent * 2 + 1;
            child2 = parent * 2 + 2;
            if (child1 > end) {
                return;
            }
            if (child2 > end) {
                child2 = child1;
            }
            parentValue = array[parent];
            child1Value = array[child1];
            child2Value = array[child2];
        }
    }
}
