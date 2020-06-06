package leetcode;

public class NumIslandsDfs {
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
            if (grid.length == 0) {
                return 0;
            }
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        DFS(i, j, grid, visited);
                        count++;
                    }
                }
            }
            return count;
        }

        private void DFS(int i, int j, char[][] grid, boolean[][] visited) {
            if (visited[i][j]) {
                return;
            }
            visited[i][j] = true;
            int[][] directions = new int[][]{
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            };
            for (int[] direction : directions) {
                int a = i + direction[0];
                int b = j + direction[1];
                if (valid(a, b, grid, visited)) {
                    DFS(a, b, grid, visited);
                }
            }
        }

        private boolean valid(int a, int b, char[][] grid, boolean[][] visited) {
            return a >= 0 && a < grid.length
                    && b >= 0 && b < grid[0].length
                    && grid[a][b] == '1' && !visited[a][b];
        }
    }
}

