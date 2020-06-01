package hackathon_1.session_1;

import com.google.gson.Gson;

public class Spiral_ChuQuocHung {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Spiral_ChuQuocHung main = new Spiral_ChuQuocHung();
        System.out.println(gson.toJson(main.spiral(new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        })));
    }
    public int[] spiral(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int[][] directions = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0},
        };
        int directionIndex = 0;
        int i = 0, j = 0;
        int[] result = new int[matrix.length * matrix[0].length];
        result[0] = matrix[0][0];
        int m = 1;
        while (true) {
            int[] direction = directions[directionIndex];
            if (canGo(i, j, top, bottom, left, right, direction)) {
                i += direction[0];
                j += direction[1];
                result[m] = matrix[i][j];
                m++;
            } else {
                switch (directionIndex) {
                    case 0:
                        top += 1;
                        break;
                    case 1:
                        right -= 1;
                        break;
                    case 2:
                        bottom -= 1;
                        break;
                    case 3:
                        left += 1;
                        break;
                }
                directionIndex = getNextDirection(directionIndex);
                direction = directions[directionIndex];
                if (!canGo(i, j, top, bottom, left, right, direction)) {
                    break;
                }
            }
        }
        return result;
    }

    boolean canGo(int i, int j, int top, int bottom, int left, int right, int[] direction) {
        return i + direction[0] <= bottom && i + direction[0] >= top
                && j + direction[1] >= left && j + direction[1] <= right;
    }

    int getNextDirection(int directionIndex) {
        return (directionIndex + 1) % 4;
    }
}
