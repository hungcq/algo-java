package graph.topological_sort;

import java.util.*;

/**
 * Created by: HungCQ
 * Date: 10-Jul-20
 */
public class TopologicalSort {

    public static final int VISITING = 0;
    public static final int VISITED = 1;

    public static List<Node> sort(Graph graph) {
        Map<Node, Integer> visited = new HashMap<>();
        List<Node> list = new ArrayList<>();
        for (Node node : graph.nodeList) {
            if (visited.containsKey(node)) {
                continue;
            }
            if (!DFS(node, visited, list)) {
                System.out.println("Cannot sort. Graph contains cycle.");
                return new ArrayList<>();
            }
        }
        Collections.reverse(list);
        return list;
    }

    private static boolean DFS(Node node, Map<Node, Integer> visited, List<Node> list) {
        if (visited.containsKey(node) && visited.get(node) == VISITING) {
            return false;
        }
        visited.put(node, VISITING);
        for (Node neighbor : node.neighbors) {
            if (visited.containsKey(neighbor) && visited.get(neighbor) == VISITED) {
                continue;
            }
            if (!DFS(neighbor, visited, list)) {
                return false;
            }
        }
        visited.put(node, VISITED);
        list.add(node);
        return true;
    }
}
