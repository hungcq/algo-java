package recusion_dynamic;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: HungCQ
 * Date: 28-Jul-20
 */
public class RobotInAGrid {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {
                        {0, 0, 0, 0, 1, 0, 0},
                        {0, 1, 0, 1, 0, 0, 0},
                        {1, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0},
                };
        RobotInAGrid robotInAGrid = new RobotInAGrid();
        System.out.println(gson.toJson(robotInAGrid.findWay(matrix)));
        int[][] matrix2 = new int[][]
                {
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0},
                        {0, 1, 0, 0, 1, 1, 0},
                        {1, 1, 0, 0, 0, 0, 0},
                };
        System.out.println(gson.toJson(robotInAGrid.findWay(matrix2)));
        int[][] matrix3 = new int[][]
                {
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                };
        System.out.println(gson.toJson(robotInAGrid.findWay(matrix3)));
    }

    int count = 0;

    List<List<String>> findWay(int[][] matrix) {
        List<List<String>> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        backtrack(i, j, matrix, new ArrayList<>(), result);
        return result;
    }

    void backtrack(int i, int j, int[][] matrix, List<String> tmp, List<List<String>> result) {
        count++;
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            result.add(new ArrayList<>(tmp));
            System.out.println(count);
            return;
        }
        if (i + 1 <= matrix.length - 1 && matrix[i + 1][j] == 0) {
            tmp.add("down");
            backtrack(i + 1, j, matrix, tmp, result);
            tmp.remove(tmp.size() - 1);
        }
        if (j + 1 <= matrix[0].length - 1 && matrix[i][j + 1] == 0) {
            tmp.add("right");
            backtrack(i, j + 1, matrix, tmp, result);
            tmp.remove(tmp.size() - 1);
        }
    }
}
