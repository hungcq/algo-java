import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println((-3) % 10);
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

        public int maxEvents(int[][] events) {
            Arrays.sort(events, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
//                    if (o1[1] != o2[1]) {
                        return o1[1] - o2[1];
//                    }
//                    return o1[0] - o2[0];
                }
            });
            int time = 0;
            int count = 0;
            for (int i = 0; i < events.length; i++) {
                if (events[i][0] < time) {
                    continue;
                }
                time = events[i][1];
                count++;
            }
            return count;
        }
    }
}
