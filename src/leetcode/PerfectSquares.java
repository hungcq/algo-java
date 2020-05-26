package leetcode;

import java.util.*;

public class PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long ts = System.currentTimeMillis();
        System.out.println(solution.numSquares( 7217, 0));
        System.out.println(System.currentTimeMillis() - ts);
        System.out.println(solution.numSquares(7217));
        System.out.println(System.currentTimeMillis() - ts);
    }

    static class Solution {
        int minStep = Integer.MAX_VALUE;

        public int numSquares(int n, int currentStep) {
            int min = Integer.MAX_VALUE;
            for (int i = (int) Math.sqrt(n); i >= 0; i--) {
                if (i * i == n) {
                    minStep = Math.min(minStep, currentStep);
                    return 1;
                }
                if (currentStep > minStep) {
                    return minStep;
                }
                int step = 1 + numSquares(n - i * i, currentStep + 1);
                min = Math.min(step, min);
            }
            return min;
        }

        public int numSquares(int n) {
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(n);
            List<Integer> squares = new ArrayList<>();
            for (int i = 1; i * i <= n; i++) {
                squares.add(i * i);
            }
            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int value = queue.poll();
                    visited.add(value);
                    if (value == 0) {
                        return step;
                    }
                    for (int square : squares) {
                        if (!visited.contains(value - square) && value - square >= 0) {
                            queue.offer(value - square);
                        }
                    }
                }
                step++;
            }
            return -1;
        }

//        /**
//         * Return the length of the shortest path between root and target node.
//         */
//        int BFS(Node root, List<Node> nodeList, int target) {
//            Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
//            int step = 0;       // number of steps neeeded from root to current node
//            int sum = 0;
//            // initialize
//            queue.add(root);
//            // BFS
//            int minStep = Integer.MAX_VALUE;
//            while (queue.size() > 0) {
//                step = step + 1;
//                // iterate the nodes which are already in the queue
//                int size = queue.size();
//                for (int i = 0; i < size; ++i) {
//                    Node cur = queue.element();
//                    if (cur == target) {
//                        return step;
//                    }
//                    for (int next : cur.adjList) {
//                        Node nextNode = nodeList.get(next);
//                            queue.add(nextNode);
//                    }
//                    queue.remove();
//                }
//            }
//            return -1;          // there is no path from root to target
//        }
//
//        static class Node {
//            int value;
//            List<Integer> adjList = new ArrayList<>();
//        }
        }
    }
