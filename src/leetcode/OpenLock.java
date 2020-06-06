package leetcode;

import java.util.*;

public class OpenLock {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] deadends1 = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target1 = "0202";
        String[] deadends2 = new String[]{"8888"};
        String target2 = "0009";
        String[] deadends3 = new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "8888";
        String[] deadends4 = new String[]{"0000"};
        String target4 = "8888";

        System.out.println(solution.openLock(deadends1, target1));
        System.out.println(solution.openLock(deadends2, target2));
        System.out.println(solution.openLock(deadends3, target3));
        System.out.println(solution.openLock(deadends4, target4));
    }

    static class Solution {
        public int openLock(String[] deadendStrs, String targetStr) {
            int[] deadEnds = new int[deadendStrs.length];
            int i = 0;
            for (String deadend : deadendStrs) {
                deadEnds[i] = Integer.parseInt(deadend);
                i++;
            }
            int target = Integer.parseInt(targetStr);
            List<Node> nodeList = new ArrayList<>();
            for (i = 0; i < 10000; i++) {
                Node node = new Node();
                node.value = i;
                int tmp = i;
                List<Integer> digits = new ArrayList<>();
                while (tmp > 0) {
                    digits.add(tmp % 10);
                    tmp /= 10;
                }
                while (digits.size() < 4) {
                    digits.add(0);
                }
                for (int j = 0; j < digits.size(); j++) {
                    int digit = digits.get(j);
                    if (digit == 9) {
                        node.adjList.add(i - 9 * (int) Math.pow(10, j));
                        node.adjList.add(i - (int) Math.pow(10, j));
                    } else if (digit == 0) {
                        node.adjList.add(i + (int) Math.pow(10, j));
                        node.adjList.add(i + 9 * (int) Math.pow(10, j));
                    } else {
                        node.adjList.add(i + (int) Math.pow(10, j));
                        node.adjList.add(i - (int) Math.pow(10, j));
                    }
                }
                nodeList.add(node);
            }
            for (int deadEnd : deadEnds) {
                Node deadNode = nodeList.get(deadEnd);
                for (int deadAdj : deadNode.adjList) {
                    nodeList.get(deadAdj).adjList.remove(Integer.valueOf(deadEnd));
                }
                deadNode.adjList.clear();
            }
            Node startNode = nodeList.get(0);
            Node targetNode = nodeList.get(target);
            int step = BFS(startNode, targetNode, nodeList);
            return step;
        }

        /**
         * Return the length of the shortest path between root and target node.
         */
        int BFS(Node root, Node target, List<Node> nodeList) {
            Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
            Set<Node> visited = new HashSet<>();  // store all the nodes that we've visited
            int step = -1;       // number of steps neeeded from root to current node
            // initialize
            queue.add(root);
            visited.add(root);
            // BFS
            while (queue.size() > 0) {
                step = step + 1;
                // iterate the nodes which are already in the queue
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    Node cur = queue.element();
                    if (cur == target) {
                        return step;
                    }
                    for (int next : cur.adjList) {
                        Node nextNode = nodeList.get(next);
                        if (!visited.contains(nextNode)) {
                            queue.add(nextNode);
                            visited.add(nextNode);
                        }
                    }
                    queue.remove();
                }
            }
            return -1;          // there is no path from root to target
        }

        static class Node {
            int value;
            List<Integer> adjList = new ArrayList<>();
        }
    }
}
