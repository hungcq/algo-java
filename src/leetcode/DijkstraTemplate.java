package leetcode;

import java.util.*;

class DijkstraTemplate {
    public int minimumEffortPath(int[][] heights) {
        Node[][] nodes = new Node[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights.length; j++) {
                Node node = new Node();
                nodes[i][j] = node;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights.length; j++) {
                Node node = nodes[i][j];
                if (i - 1 >= 0) {
                    node.adjList.add(new Edge(nodes[i -1][j],Math.abs(heights[i][j] - heights[i - 1][j])));
                }
                if (i + 1 < heights.length) {
                    node.adjList.add(new Edge(nodes[i +1][j],Math.abs(heights[i][j] - heights[i + 1][j])));
                }
                if (j - 1 >= 0) {
                    node.adjList.add(new Edge(nodes[i][j -1],Math.abs(heights[i][j] - heights[i][j - 1])));
                }
                if (j + 1 < heights[0].length) {
                    node.adjList.add(new Edge(nodes[i][j +1],Math.abs(heights[i][j] - heights[i][j + 1])));
                }
            }
        }
        return dijkstra(nodes[0][0], nodes[heights.length - 1][heights[0].length - 1]);
    }

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

    class Node {
        String name;
        List<Edge> adjList = new ArrayList<>();
    }

    class NodeDis {
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

    class Edge {
        public Node neighbor;
        public int weight;

        public Edge(Node neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

    int dijkstra(Node start, Node end) {
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
            for (Edge edge : nodeDis.node.adjList) {
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
}