package leetcode;

import java.util.*;

public class QueueReconstruction {
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Map<Integer, List<int[]>> map = new HashMap<>();
            for (int[] person : people) {
                int numAhead = person[1];
                int height = person[0];
                List<int[]> list = null;
                if (!map.containsKey(numAhead)) {
                    list = new ArrayList<>();
                    map.put(numAhead, list);
                } else {
                    list = map.get(numAhead);
                }
                list.add(person);
            }
            for (List<int []> list : map.values()) {
                list.sort(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o2[0] - o1[0];
                    }
                });
            }
            List<int[]> result = new ArrayList<>();
            for (int i = 0; i < people.length; i++) {
                List<int []> list = map.get(i);
                if (list == null) {
                    continue;
                }
                if (i == 0) {
                    for (int j = list.size() - 1; j >= 0; j--) {
                        int[] person = list.get(j);
                        result.add(person);
                    }
                    continue;
                }
                for (int[] person : list) {
                    int height = person[0];
                    int numAhead = person[1];
                    int j;
                    for (j = 0; j < result.size(); j++) {
                        int[] other = result.get(j);
                        if (height <= other[0]) {
                            numAhead--;
                        }
                        if (numAhead == 0) {
                            break;
                        }
                    }
                    result.add(j + 1, person);
                }
            }
            return result.toArray(new int[0][0]);
        }
    }
}
