package gd_mindx.session_17;

import org.junit.Test;

import java.util.Comparator;

/**
 * Created by: HungCQ
 * Date: 27-Aug-20
 */
public class HeapTest {
    @Test
    public void test() {
        Heap<Integer> heap = new Heap<>(Comparator.comparingInt(o -> o));
        heap.add(3);
        heap.add(7);
        heap.add(23);
        heap.add(10);
        heap.add(46);
        heap.add(87);
        heap.add(31);
        heap.remove(10);
        heap.remove(31);
        heap.remove(87);
        while (!heap.isEmpty()) {
            System.out.println(heap.extract());
        }
    }
}