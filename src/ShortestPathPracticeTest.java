import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ShortestPathPracticeTest {

    @Test
    public void dijkstraTest() {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        int start = 1;
        int end = 4;
        Map<Integer, Integer> adj = new HashMap<>();
        adj.put(3, 1);
        adj.put(6, 1);
        graph.put(5, adj);
        adj = new HashMap<>();
        adj.put(11, 4);
        adj.put(4, 5);
        graph.put(3, adj);
        adj = new HashMap<>();
        adj.put(10, 3);
        graph.put(4, adj);
        adj = new HashMap<>();
        adj.put(3, 4);
        graph.put(2, adj);
        adj = new HashMap<>();
        adj.put(4, 20);
        adj.put(2, 3);
        adj.put(5, 7);
        graph.put(1, adj);
        adj = new HashMap<>();
        adj.put(7, 1);
        graph.put(6, adj);
        adj = new HashMap<>();
        adj.put(8, 1);
        graph.put(7, adj);
        adj = new HashMap<>();
        adj.put(4, 1);
        graph.put(8, adj);
        graph.put(10, new HashMap<>());
        graph.put(11, new HashMap<>());
        System.out.println(ShortestPathPractice.dijkstra(start, end, graph));
        System.out.println(ShortestPathPractice.dijkstra(8, 11, graph));
        System.out.println(ShortestPathPractice.dijkstra(5, 11, graph));
        System.out.println(ShortestPathPractice.dijkstra(5, 10, graph));
    }

    @Test
    public void heapTest() {
        ShortestPathPractice.Heap heap = new ShortestPathPractice.Heap(new Comparator<ShortestPathPractice.NodeDis>() {
            @Override
            public int compare(ShortestPathPractice.NodeDis o1, ShortestPathPractice.NodeDis o2) {
                return o1.distance - o2.distance;
            }
        });
        ShortestPathPractice.NodeDis updateObj1 = new ShortestPathPractice.NodeDis(1, 9);
        ShortestPathPractice.NodeDis updateObj2 = new ShortestPathPractice.NodeDis(10, 4);
        heap.add(new ShortestPathPractice.NodeDis(3, 3));
        heap.add(new ShortestPathPractice.NodeDis(4, 7));
        heap.add(updateObj2);
        heap.add(new ShortestPathPractice.NodeDis(5, 10));
        heap.add(new ShortestPathPractice.NodeDis(6, 6));
        heap.add(updateObj1);
        heap.add(new ShortestPathPractice.NodeDis(7, 8));
        heap.add(new ShortestPathPractice.NodeDis(8, 20));
        heap.add(new ShortestPathPractice.NodeDis(9, 1));
        heap.update(new ShortestPathPractice.NodeDis(1, 5));
        heap.update(new ShortestPathPractice.NodeDis(10, 17));
        ShortestPathPractice.NodeDis value = heap.extract();
        while (value != null) {
            System.out.println(value.distance);
            value = heap.extract();
        }
    }
}