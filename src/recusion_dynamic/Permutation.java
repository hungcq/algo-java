package recusion_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
}
