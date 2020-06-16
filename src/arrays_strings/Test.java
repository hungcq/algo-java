package arrays_strings;

/**
 * Created by: HungCQ
 * Date: 16-Jun-20
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
//        System.out.println(test.oneAway("pale", "ple"));
//        System.out.println(test.oneAway("pale", "pasle"));
//        System.out.println(test.oneAway("pale", "bake"));
//        System.out.println(test.oneAway("ple", "pale"));
//        System.out.println(test.oneAway("pasle", "paie"));

        System.out.println(test.stringCompression("aabcccccaaa"));
        System.out.println(test.stringCompression("abc"));
        System.out.println(test.stringCompression("a"));
        System.out.println(test.stringCompression("aaa"));
        System.out.println(test.stringCompression(""));
        System.out.println(test.stringCompression("aabbccc"));

        int[][] matrix = new int[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25},
        };
        test.rotateImage(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.print("\n");
        }
    }

    boolean oneAway(String s1, String s2) {
        int dif = s1.length() - s2.length();
        if (Math.abs(dif) >= 2) {
            return false;
        }
        char[] longStr = dif == 1 ? s1.toCharArray() : s2.toCharArray();
        char[] shortStr = dif == 1 ? s2.toCharArray() : s1.toCharArray();
        boolean flag = false;
        int i = 0, j = 0;
        while (i < shortStr.length) {
            char c1 = shortStr[i];
            char c2 = longStr[j];
            if (c1 != c2) {
                if (flag) {
                    return false;
                }
                flag = true;
                if (Math.abs(dif) == 1) {
                    i--;
                }
            }
            i++;
            j++;
        }
        return true;
    }

    String stringCompression(String s) {
        if (s.length() <= 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        char lastChar = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == lastChar) {
                count++;
            } else {
                builder.append(lastChar);
                builder.append(count);
                lastChar = c;
                count = 1;
            }
        }
        builder.append(lastChar);
        builder.append(count);
        return builder.length() < s.length() ? builder.toString() : s;
    }

    void rotateImage(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int j2 = n - i - 1;
                int j3 = n - j - 1;
                int j4 = n - j2 - 1;
                int tmp = matrix[j3][j4];
                matrix[j3][j4] = matrix[j2][j3];
                matrix[j2][j3] = matrix[j][j2];
                matrix[j][j2] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }
}
