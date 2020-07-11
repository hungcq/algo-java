package gd_mindx.session_10;


import org.junit.Test;
import org.junit.Assert;

import java.util.*;

public class MaxHeapTest {

    @Test
    public void test() {
        int[] array = new int[]{5, 10, 5, 4, 17, 3, 4, 50, 11, 3, 62};
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.add(array);
        Arrays.sort(array);
        System.out.println(maxHeap.getMax());
        int max = maxHeap.extractMax();
        List<Integer> result = new ArrayList<>();
        while (max != -1) {
            result.add(0, max);
            System.out.println(maxHeap.getMax());
            max = maxHeap.extractMax();
        }
        Assert.assertArrayEquals(array, result.stream().mapToInt(i->i).toArray());
    }

    @Test
    public void heapSortTest() {
        int[] array = new int[]{5, 10, 5, 4, 17, 3, 4, 50, 11, 3, 62};
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.add(array);
        Arrays.sort(array);
        List<Integer> sortedList = maxHeap.heapSort();
        Assert.assertArrayEquals(array, sortedList.stream().mapToInt(i->i).toArray());
    }

    @Test
    public void buildHeapFromArray() {
        Integer[] array = new Integer[]{5, 10, 5, 4, 17, 3, 4, 50, 11, 3, 62};
        List<Integer> list = Arrays.asList(array);
        MaxHeap maxHeap = MaxHeap.buildHeapFromList(list);
        Arrays.sort(array);
        System.out.println(maxHeap.getMax());
        int max = maxHeap.extractMax();
        List<Integer> result = new ArrayList<>();
        while (max != -1) {
            result.add(0, max);
            System.out.println(maxHeap.getMax());
            max = maxHeap.extractMax();
        }
        Assert.assertArrayEquals(array, result.toArray(new Integer[0]));
    }

    @Test
    public void add() {
    }

    @Test
    public void getMax() {
    }

    @Test
    public void extractMax() {
    }
}