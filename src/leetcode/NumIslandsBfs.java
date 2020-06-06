package leetcode;

import java.util.*;

public class NumIslandsBfs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid1 = new char[][]
                {
                        {
                                '1', '1', '1', '1', '0'
                        },
                        {
                                '1', '1', '0', '1', '0'
                        },
                        {
                                '1', '1', '0', '0', '0'
                        },
                        {
                                '0', '0', '0', '0', '0'
                        },
                };
        char[][] grid2 = new char[][]
                {
                        {
                                '1', '1', '0', '0', '0'
                        },
                        {
                                '1', '1', '0', '0', '0'
                        },
                        {
                                '0', '0', '1', '0', '0'
                        },
                        {
                                '0', '0', '0', '1', '1'
                        },
                };
        System.out.println(solution.numIslands(grid1));
        System.out.println(solution.numIslands(grid2));
    }

    static class Solution {
        public int numIslands(char[][] grid) {
            List<Node> nodeList = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    Node node = new Node();
                    nodeList.add(node);
                    node.value = grid[i][j];
                    if (grid[i][j] == '0') {
                        continue;
                    }
                    int a = i - 1;
                    int b = j;
                    checkAndAddToAdjList(grid, node, a, b);
                    a = i + 1;
                    b = j;
                    checkAndAddToAdjList(grid, node, a, b);
                    a = i;
                    b = j - 1;
                    checkAndAddToAdjList(grid, node, a, b);
                    a = i;
                    b = j + 1;
                    checkAndAddToAdjList(grid, node, a, b);
                }
            }
            int islandCount = 0;
            Set<Node> visited = new HashSet<>();
            for (Node node : nodeList) {
                if (!visited.contains(node) && node.value == '1') {
                    BFS(node, null, visited, nodeList);
                    islandCount++;
                }
            }
            return islandCount;
        }

        void checkAndAddToAdjList(char[][] grid, Node node, int a, int b) {
            if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length) {
                if (grid[a][b] == '1') {
                    node.list.add(a * grid[0].length + b);
                }
            }
        }

        /**
         * Return the length of the shortest path between root and target node.
         */
        int BFS(Node root, Node target, Set<Node> visitedAll, List<Node> nodeList) {
            Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
            Set<Node> visited = new HashSet<>();  // store all the nodes that we've visited
            int step = 0;       // number of steps neeeded from root to current node
            // initialize
            queue.add(root);
            visited.add(root);
            visitedAll.add(root);
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
                    for (int next : cur.list) {
                        Node nextNode = nodeList.get(next);
                        if (!visited.contains(nextNode)) {
                            queue.add(nextNode);
                            visited.add(nextNode);
                            visitedAll.add(nextNode);
                        }
                    }
                    queue.remove();
                }
            }
            return -1;          // there is no path from root to target
        }

        static class Node {
            int value;
            List<Integer> list = new ArrayList<>();
        }
    }
}
