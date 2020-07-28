package recusion_dynamic;

import java.util.Arrays;

/**
 * Created by: HungCQ
 * Date: 28-Jul-20
 */
public class TripleStep {

    public static void main(String[] args) {
        TripleStep tripleStep = new TripleStep();
        System.out.println(tripleStep.countWays(14));
        System.out.println(tripleStep.countWays2(14));
    }

    int countWays(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return countWays(n, memo);
    }

    int countWays(int n, int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] >-1){
            return memo[n];
        } else{
            memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) +
                    countWays(n - 3, memo);
            return memo[n];
        }
    }

    int countWays2(int n) {
        if (n <= 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }
        int a = 1;
        int b = 2;
        int c = 4;
        for (int i = 4; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }
}
