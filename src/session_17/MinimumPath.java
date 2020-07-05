package session_17;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MinimumPath {

    public static int dijkstra(Node start, Node end) {
        Heap queue = new Heap(new Comparator<NodeDis>() {
            @Override
            public int compare(NodeDis o1, NodeDis o2) {
                return o2.dis - o1.dis;
            }
        });
        NodeDis startNodeDis = new NodeDis(start);
        startNodeDis.dis = 0;
        queue.add(startNodeDis);
        Map<Node, NodeDis> disMap = new HashMap<>();
        disMap.put(start, startNodeDis);
        while (!queue.isEmpty()) {
            NodeDis nodeDis = (NodeDis) queue.extract();
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
                    queue.siftDown(nodeDis1);
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
        void relax(int newDis) {
            dis = Math.min(dis, newDis);
        }
    }
}
