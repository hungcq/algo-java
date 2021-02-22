import java.util.*;

public class ShortestPathPractice {

    static class NodeDis {
        int id;
        int distance;
        public NodeDis(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }

    public static int dijkstra(int start, int end, Map<Integer, Map<Integer, Integer>> graph) {
        Heap heap = new Heap(new Comparator<NodeDis>() {
            @Override
            public int compare(NodeDis o1, NodeDis o2) {
                return o2.distance - o1.distance;
            }
        });
        heap.add(new NodeDis(start, 0));
        while (heap.size() > 0) {
            NodeDis nodeDis = heap.extract();
            int node = nodeDis.id;
            Map<Integer, Integer> adjNodes = graph.get(node);
            if (node == end) {
                return nodeDis.distance;
            }
            for (Map.Entry<Integer, Integer> entry : adjNodes.entrySet()) {
                int nodeId = entry.getKey();
                int distance = entry.getValue();
                heap.add(new NodeDis(nodeId, Integer.MAX_VALUE));
                heap.reduce(new NodeDis(nodeId, distance + nodeDis.distance));
            }
        }
        return -1;
    }

    static class Heap {
        private List<NodeDis> list = new ArrayList<>();
        private Comparator<NodeDis> comparator;
        private Map<Integer, Integer> positionMap = new HashMap<>();

        public Heap(Comparator<NodeDis> comparator) {
            this.comparator = comparator;
        }

        public int size() {
            return list.size();
        }

        public void add(NodeDis value) {
            if (positionMap.containsKey(value.id)) {
                return;
            }
            list.add(value);
            positionMap.put(value.id, list.size() - 1);
            siftUp(list.size() - 1);
        }

        public NodeDis extract() {
            if (list.size() == 0) {
                return null;
            }
            NodeDis value = list.get(0);
            swap(0, list.size() - 1);
            list.remove(list.size() - 1);
            if (list.size() == 0) {
                return value;
            }
            positionMap.remove(list.get(list.size() - 1).id);
            siftDown(0);
            return value;
        }

        public NodeDis getMax() {
            return list.get(0);
        }

        public void update(NodeDis value) {
            NodeDis oldValue = list.get(positionMap.get(value.id));
            if (comparator.compare(oldValue, value) == 0) {
                return;
            }
            int position = positionMap.get(value.id);
            list.set(position, value);
            if (comparator.compare(oldValue, value) < 0) {
                siftUp(position);
            } else {
                siftDown(position);
            }
        }

        public void reduce(NodeDis value) {
            NodeDis oldValue = list.get(positionMap.get(value.id));
            if (comparator.compare(oldValue, value) >= 0) {
                return;
            }
            int position = positionMap.get(value.id);
            list.set(position, value);
            siftUp(position);
        }

        private void siftUp(int position) {
            int parent = (position - 1) / 2;
            while (position != parent && comparator.compare(list.get(position), list.get(parent)) > 0) {
                swap(position, parent);
                position = parent;
                parent = (position - 1) / 2;
            }
        }

        private void siftDown(int position) {
            int child1 = position * 2 + 1;
            int child2 = position * 2 + 2;
            while (child2 < list.size()) {
                if (comparator.compare(list.get(child1), list.get(child2)) > 0) {
                    if (comparator.compare(list.get(position), list.get(child1)) < 0) {
                        swap(position, child1);
                        position = child1;
                    } else {
                        break;
                    }
                } else {
                    if (comparator.compare(list.get(position), list.get(child2)) < 0) {
                        swap(position, child2);
                        position = child2;
                    } else {
                        break;
                    }
                }
                child1 = position * 2 + 1;
                child2 = position * 2 + 2;
            }
            if (child1 < list.size() && comparator.compare(list.get(position), list.get(child1)) < 0) {
                swap(position, child1);
            }
        }

        private void swap(int position1, int position2) {
            Collections.swap(list, position1, position2);
            positionMap.put(list.get(position1).id, position1);
            positionMap.put(list.get(position2).id, position2);
        }
    }
}
