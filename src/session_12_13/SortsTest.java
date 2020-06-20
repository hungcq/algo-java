package session_12_13;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortsTest {

    private static final int LENGTH = 1000;

    @Test
    public void bubbleSort() {
        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            array1[i] = random;
            array2[i] = random;
        }
        Arrays.sort(array1);
        Sorts.bubbleSort(array2);
        Assert.assertArrayEquals(array1, array2);
        Sorts.bubbleSort(new int[0]);
    }

    @Test
    public void selectionSort() {
        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            array1[i] = random;
            array2[i] = random;
        }
        Arrays.sort(array1);
        Sorts.selectionSort(array2);
        Assert.assertArrayEquals(array1, array2);
        Sorts.selectionSort(new int[0]);
    }

    @Test
    public void insertionSort() {
        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            array1[i] = random;
            array2[i] = random;
        }
        Arrays.sort(array1);
        Sorts.insertionSort(array2);
        Assert.assertArrayEquals(array1, array2);
        Sorts.insertionSort(new int[0]);
    }

    @Test
    public void heapSort() {
        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            array1[i] = random;
            array2[i] = random;
        }
        Arrays.sort(array1);
        Sorts.heapSort(array2);
        Assert.assertArrayEquals(array1, array2);
        Sorts.heapSort(new int[0]);
    }

    @Test
    public void mergeSort() {
        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            array1[i] = random;
            array2[i] = random;
        }
        Arrays.sort(array1);
        Sorts.mergeSort(array2);
        Assert.assertArrayEquals(array1, array2);
        Sorts.mergeSort(new int[0]);
    }

    @Test
    public void quickSort() {
        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            array1[i] = random;
            array2[i] = random;
        }
        Arrays.sort(array1);
        Sorts.quickSort(array2);
        Assert.assertArrayEquals(array1, array2);
        Sorts.quickSort(new int[0]);
    }
}