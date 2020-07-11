package gd_mindx.session_10;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    List<Integer> list = new ArrayList<>();

    public MaxHeap() {

    }

    public static MaxHeap buildHeapFromList(List<Integer> initialList) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.list.addAll(initialList);
        int lastNonLeafNodeIndex = maxHeap.list.size() / 2 - 1;
        while (lastNonLeafNodeIndex >= 0) {
            maxHeap.siftDown(maxHeap.list, lastNonLeafNodeIndex, maxHeap.list.size() - 1);
            lastNonLeafNodeIndex--;
        }
        return maxHeap;
    }

    public void add(int val) {
        list.add(val);
        int index = list.size() - 1;
        int parent = (index - 1) / 2;
        while (index != parent && list.get(index) > list.get(parent)) {
            swap(list, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void add(int... vals) {
        for (int val : vals) {
            add(val);
        }
    }

    public int getMax() {
        if (list.size() == 0) {
            return -1;
        }
        return list.get(0);
    }

    public int extractMax() {
        if (list.size() == 0) {
            return -1;
        }
        if (list.size() == 1) {
            return list.remove(0);
        }
        int max = list.get(0);
        int last = list.remove(list.size() - 1);
        list.set(0, last);
        siftDown(list, 0, list.size() - 1);
        return max;
    }

    public List<Integer> heapSort() {
        List<Integer> result = new ArrayList<>(list);
        if (result.size() <= 1) {
            return result;
        }
        int end = result.size() - 1;
        while (end > 0) {
            swap(result, 0, end);
            end -= 1;
            siftDown(result, 0, end);
        }
        return result;
    }

    public int[] asArray() {
        return list.stream().mapToInt(i->i).toArray();
    }

    private void siftDown(List<Integer> list, int position, int end) {
        int parent = position;
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        if (child1 > end) {
            return;
        }
        if (child2 > end) {
            child2 = child1;
        }
        int parentValue = list.get(parent);
        int child1Value = list.get(child1);
        int child2Value = list.get(child2);
        while (parentValue < child1Value || parentValue < child2Value) {
            if (child1Value > child2Value) {
                swap(list, parent, child1);
                parent = child1;
            } else {
                swap(list, parent, child2);
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
            parentValue = list.get(parent);
            child1Value = list.get(child1);
            child2Value = list.get(child2);
        }
    }

    private void swap(List<Integer> list, int index1, int index2) {
        int tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
    }
}
