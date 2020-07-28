package leetcode;

/**
 * Created by: HungCQ
 * Date: 14-Jul-20
 */
public class SubMatrixAllOne {
    public int numSubmat(int[][] mat) {
        int[][][] tmp = new int[mat.length][mat[0].length][2];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int val = mat[i][j];
                if (val == 1) {
                    int left = 0;
                    int up = 0;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        up = Math.min(tmp[i - 1][j][1], tmp[i][j - 1][1]) + 1;
                        left = Math.min(tmp[i][j - 1][0], tmp[i][j - 1][0]) + 1;
                    }
                    if (j - 1 >= 0) {
                        left = tmp[i][j - 1][0] + 1;
                    }
                    tmp[i][j] = new int[]{left, up};
                } else {
                    tmp[i][j] = new int[]{-1, -1};
                }
            }
        }
        int sum = 0;
        int[] numLeft = new int[mat.length];
        int[] numUp = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int val = mat[i][j];
                if (val == 1) {
                    numLeft[i]++;
                    numUp[j]++;
                    sum += numLeft[i];
                    sum += numUp[j];
                    sum -= 1;
                    sum += tmp[i][j][0] * tmp[i][j][1];
                } else {
                    numLeft[i] = 0;
                    numUp[j] = 0;
                }
            }
        }
        return sum;
    }
}
