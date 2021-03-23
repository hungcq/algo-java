package graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopoSortTest {
    @Test
    public void topologicalSort() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(4));
        graph.put(2, Arrays.asList(4));
        graph.put(3, null);
        graph.put(4, Arrays.asList(3));
//        graph.put(4, Arrays.asList(3, 6));
        graph.put(5, null);
        graph.put(6, Arrays.asList(1, 2));
        TopoSort.topologicalSort(graph);
    }
}