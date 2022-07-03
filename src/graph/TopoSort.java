package graph;

import java.util.*;

public class TopoSort {

    public static int VISITING = 1;
    public static int VISITED = 2;

    public static void topologicalSort(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> visited = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int node : graph.keySet()) {
            if (visited.containsKey(node)) {
                continue;
            }
            if (!dfs(graph, node, visited, result)) {
                System.out.println("error: graph contains cycle");
                return;
            }
        }
        Collections.reverse(result);
        for (int node : result) {
            System.out.println(node);
        }
    }

    private static boolean dfs(Map<Integer, List<Integer>> graph, int node,
                            Map<Integer, Integer> visited, List<Integer> result) {
        if (visited.containsKey(node) && visited.get(node) == VISITING) {
            return false;
        }
        visited.put(node, VISITING);
        if (graph.get(node) != null) {
            for (int neighbor : graph.get(node)) {
                if (visited.containsKey(neighbor) && visited.get(neighbor) == VISITED) {
                    continue;
                }
                if (!dfs(graph, neighbor, visited, result)) {
                    return false;
                }
            }
        }
        visited.put(node, VISITED);
        result.add(node);
        return true;
    }
}
