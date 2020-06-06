package hackathon_1.session_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoGroup_ChuQuocHung {

    public static void main(String[] args) {
        TwoGroup_ChuQuocHung twoGroup = new TwoGroup_ChuQuocHung();
        int[][] dislike1 = new int[][]{
                {1, 2},
                {1, 3},
                {2, 4}
        };
        System.out.println(twoGroup.twoGroup(4, dislike1));
        int[][] dislike2 = new int[][]{
                {1, 2},
                {1, 3},
                {2, 3}
        };
        System.out.println(twoGroup.twoGroup(4, dislike2));
        int[][] dislike3 = new int[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {1, 5},
        };
        System.out.println(twoGroup.twoGroup(5, dislike3));
    }

    public boolean twoGroup(int n, int[][] dislikes) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] dislike : dislikes) {
            Set<Integer> set;
            if (!map.containsKey(dislike[0])) {
                set = new HashSet<>();
                map.put(dislike[0], set);
            } else {
                set = map.get(dislike[0]);
            }
            set.add(dislike[1]);
            Set<Integer> set2;
            if (!map.containsKey(dislike[1])) {
                set2 = new HashSet<>();
                map.put(dislike[1], set2);
            } else {
                set2 = map.get(dislike[1]);
            }
            set2.add(dislike[0]);
        }
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (isCyclic(i, visited, map, -1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCyclic(int start, boolean[] visited, Map<Integer, Set<Integer>> map, int parent) {
        visited[start] = true;
        Set<Integer> set = map.get(start);
        for (int i : set) {
            if (!visited[i]) {
                if (isCyclic(i, visited, map, start)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }
}
