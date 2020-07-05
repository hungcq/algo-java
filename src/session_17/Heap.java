package session_17;

import java.util.*;

public class Heap {
    List<Object> list = new ArrayList<>();
    Comparator comparator;
    Map<Object, Integer> positionMap = new HashMap<>();

    public Heap(Comparator comparator) {
        this.comparator = comparator;
    }

    public void add(Object object) {
        list.add(object);
        positionMap.put(object, list.size() - 1);
        int index = list.size() - 1;
        int parent = (index - 1) / 2;
        while (index != parent && comparator.compare(list.get(index), list.get(parent)) > 0) {
            swap(list, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public Object extract() {
        if (list.size() == 0) {
            return -1;
        }
        if (list.size() == 1) {
            return list.remove(0);
        }
        Object max = list.get(0);
        Object last = list.remove(list.size() - 1);
        list.set(0, last);
        siftDown(0, list.size() - 1);
        return max;
    }

    public void siftDown(Object object) {
        siftDown(positionMap.get(object), list.size() - 1);
    }

    private void siftDown(int position, int end) {
        int parent = position;
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        if (child1 > end) {
            return;
        }
        if (child2 > end) {
            child2 = child1;
        }
        Object parentValue = list.get(parent);
        Object child1Value = list.get(child1);
        Object child2Value = list.get(child2);
        while (comparator.compare(parentValue, child1Value) < 0 || comparator.compare(parentValue, child2Value) < 0) {
            if (comparator.compare(child1Value, child2Value) > 0) {
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

    private void swap(List<Object> list, int index1, int index2) {
        Object tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
        positionMap.put(list.get(index1), index1);
        positionMap.put(list.get(index2), index2);
    }

    boolean isEmpty() {
        return list.size() == 0;
    }
}
