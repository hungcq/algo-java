package recusion_dynamic;

import java.util.*;
import java.util.stream.Collectors;

public class Permutation {
    // including duplicate
    public static List<List<Integer>> permute(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, array, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, int[] array, int i) {
        if (i == array.length - 1) {
            result.add(Arrays.stream(array).boxed().collect(Collectors.toList()));
            return;
        }
        for (int j = i; j < array.length; j++) {
            swap(array, i, j);
            backtrack(result, array, i + 1);
            swap(array, i, j);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // no duplicate
    public static List<List<Integer>> permuteNoDup(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        backtrack(result, new ArrayList<>(), array.length, map);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> list, int length, Map<Integer, Integer> map) {
        if (list.size() == length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i : map.keySet()) {
            int count = map.get(i);
            if (count == 0) {
                continue;
            }
            map.put(i, count - 1);
            list.add(i);
            backtrack(result, list, length, map);
            list.remove(list.size() - 1);
            map.put(i, count);
        }
    }
}
