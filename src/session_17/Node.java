package session_17;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    List<Edge> adjList = new ArrayList<>();

    public static class Edge {
        public Node neighbor;
        public int weight;

        public Edge(Node neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
}
