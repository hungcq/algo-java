package leetcode;

import com.google.gson.Gson;

/**
 * Created by: HungCQ
 * Date: 25-May-20
 */
public class Matrix01 {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        Matrix01 matrix01 = new Matrix01();
        int[][] result = matrix01.updateMatrix(matrix);
        System.out.println(gson.toJson(result));
    }

    public int[][] updateMatrix(int[][] matrix) {
        int[][] directions = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        update(matrix, directions, 1);
        return matrix;
    }

    void update(int[][] matrix, int[][] directions, int max) {
        boolean updated = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == max) {
                    if(check(i, j, matrix, directions, max)) {
                        matrix[i][j] = max + 1;
                        updated = true;
                    }
                }
            }
        }
        if (updated) {
            update(matrix, directions, max + 1);
        }
    }

    private boolean check(int a, int b, int[][] matrix, int[][] directions, int max) {
        for (int[] direction : directions) {
            int a1 = a + direction[0];
            int b1 = b + direction[1];
            if (a1 >= 0 && a1 < matrix.length
                    && b1 >= 0 && b1 < matrix[0].length
                    && matrix[a1][b1] < max) {
                return false;
            }
        }
        return true;
    }
}
