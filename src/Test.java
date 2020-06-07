import java.util.*;

public class Test {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] getStrongest(int[] arr, int k) {
            Arrays.sort(arr);
            int median = arr[(arr.length - 1) / 2];
            List<Integer> list = new ArrayList<>();
            int count = 0;
            int i = 0;
            int j = arr.length - 1;
            while (count < k) {
                int gap1 = Math.abs(arr[i] - median);
                int gap2 = Math.abs(arr[j] - median);
                if (gap2 >= gap1) {
                    list.add(arr[j]);
                    j--;
                } else {
                    list.add(arr[i]);
                    i++;
                }
                count++;
            }
            int[] result = new int[list.size()];
            for (i = 0; i < result.length; i++) {
                result[i] = list.get(i);
            }
            return result;
        }
    }
}
