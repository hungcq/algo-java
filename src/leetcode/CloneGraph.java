package leetcode;

import java.util.*;

/**
 * Created by: HungCQ
 * Date: 25-May-20
 */
class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node clone = new Node(node.val);
        Set<Node> visited = new HashSet<>();
        Map<Integer, Node> map = new HashMap<>();
        map.put(node.val, clone);
        DFS(node, clone, visited, map);
        // Set<Node> visited2 = new HashSet<>();
        // DFS(clone, visited2);
        return clone;
    }

    private void DFS(Node root, Node clone, Set<Node> visited, Map<Integer, Node> map) {
        if (visited.contains(root)) {
            return;
        }
        visited.add(root);
        for (Node node : root.neighbors) {
            Node cloneNode = null;
            if (!map.containsKey(node.val)) {
                cloneNode = new Node(node.val);
            } else {
                cloneNode = map.get(node.val);
            }
            clone.neighbors.add(cloneNode);
            map.put(node.val, cloneNode);
            if (!visited.contains(node)) {
                DFS(node, cloneNode, visited, map);
            }
        }
    }

    private void DFS(Node root, Set<Node> visited) {
        if (visited.contains(root)) {
            return;
        }
        visited.add(root);
        System.out.print(root.val + ": ");
        for (Node node : root.neighbors) {
            System.out.print(node.val + ",");
        }
        System.out.print("\n");
        for (Node node : root.neighbors) {
            if (!visited.contains(node)) {
                DFS(node, visited);
            }
        }
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
