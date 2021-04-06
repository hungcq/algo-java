package graph;

import java.util.*;

public class ShortestPath {

    // time complexity: V + ElogV
    // space complexity V
    public static int dijkstra(Node start, Node end) {
        Heap<NodeDis> queue = new Heap<>((o1, o2) -> o2.dis - o1.dis);
        NodeDis startNodeDis = new NodeDis(start);
        startNodeDis.dis = 0;
        queue.add(startNodeDis);
        Map<Node, NodeDis> disMap = new HashMap<>();
        disMap.put(start, startNodeDis);
        while (!queue.isEmpty()) {
            NodeDis nodeDis = queue.extract();
            if (nodeDis.node == end) {
                return nodeDis.dis;
            }
            for (Node.Edge edge : nodeDis.node.adjList) {
                Node neighbor = edge.neighbor;
                int weight = edge.weight;
                NodeDis nodeDis1;
                if (!disMap.containsKey(neighbor)) {
                    nodeDis1 = new NodeDis(neighbor);
                    disMap.put(neighbor, nodeDis1);
                    nodeDis1.relax(weight + nodeDis.dis);
                    queue.add(nodeDis1);
                } else {
                    nodeDis1 = disMap.get(neighbor);
                    nodeDis1.relax(weight + nodeDis.dis);
                    queue.reposition(nodeDis1);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static class NodeDis {
        Node node;
        int dis;
        NodeDis(Node node) {
            this.node = node;
            dis = Integer.MAX_VALUE;
        }
        boolean relax(int newDis) {
            int tmp = dis;
            dis = Math.min(dis, newDis);
            return tmp != dis;
        }
    }

    // time complexity: VE
    // space complexity: V
    public static int bellmanFord(Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node.Edge, Node> edges = new HashMap<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node);
            for (Node.Edge edge : node.adjList) {
                edges.put(edge, node);
                if (!visited.contains(edge.neighbor)) {
                    queue.offer(edge.neighbor);
                }
            }
        }
        Map<Node, NodeDis> disMap = new HashMap<>();
        NodeDis startNodeDis = new NodeDis(start);
        startNodeDis.dis = 0;
        disMap.put(start, startNodeDis);
        for (int i = 0; i < visited.size() - 1; i++) {
            for (Node.Edge edge : edges.keySet()) {
                Node source = edges.get(edge);
                if (disMap.containsKey(source)) {
                    if (disMap.containsKey(edge.neighbor)) {
                        disMap.get(edge.neighbor).relax(disMap.get(source).dis + edge.weight);
                    } else {
                        NodeDis nodeDis = new NodeDis(edge.neighbor);
                        nodeDis.dis = disMap.get(source).dis + edge.weight;
                        disMap.put(edge.neighbor, nodeDis);
                    }
                }
            }
        }
        for (Node.Edge edge : edges.keySet()) {
            Node source = edges.get(edge);
            if (disMap.get(edge.neighbor).relax(disMap.get(source).dis + edge.weight)) {
                return -1;
            }
        }
        return disMap.get(end).dis;
    }
}
