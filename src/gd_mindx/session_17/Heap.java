package gd_mindx.session_17;

import com.sun.istack.internal.Nullable;

import java.util.*;

public class Heap<T> {
    List<T> list = new ArrayList<>();
    Comparator<T> comparator;
    Map<T, Integer> positionMap = new HashMap<>();

    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T object) {
        list.add(object);
        positionMap.put(object, list.size() - 1);
        int index = list.size() - 1;
        siftUp(index);
    }

    @Nullable
    public T getTop() {
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public T extract() {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            T object = list.remove(0);
            positionMap.remove(object);
            return object;
        }
        T object = list.get(0);
        T last = list.remove(list.size() - 1);
        positionMap.remove(object);
        positionMap.put(last, 0);
        list.set(0, last);
        siftDown(0);
        return object;
    }

    public void reposition(T object) {
        if (!positionMap.containsKey(object)) {
            return;
        }
        reposition(positionMap.get(object));
    }

    public void remove(T object) {
        if (!positionMap.containsKey(object)) {
            return;
        }
        if (list.size() == 1) {
            list.remove(0);
            positionMap.remove(object);
            return;
        }
        int position = positionMap.get(object);
        T last = list.remove(list.size() - 1);
        positionMap.remove(object);
        positionMap.put(last, position);
        list.set(position, last);
        reposition(position);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    private void reposition(int position) {
        int parent = (position - 1) / 2;
        if (comparator.compare(list.get(position), list.get(parent)) > 0 && position != parent) {
            siftUp(position);
        } else {
            siftDown(position);
        }
    }

    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        while (index != parent && comparator.compare(list.get(index), list.get(parent)) > 0) {
            swap(list, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void siftDown(int position) {
        int end = list.size() - 1;
        int parent = position;
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        if (child1 > end) {
            return;
        }
        if (child2 > end) {
            child2 = child1;
        }
        T parentValue = list.get(parent);
        T child1Value = list.get(child1);
        T child2Value = list.get(child2);
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

    private void swap(List<T> list, int index1, int index2) {
        T tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
        positionMap.put(list.get(index1), index1);
        positionMap.put(list.get(index2), index2);
    }
}
