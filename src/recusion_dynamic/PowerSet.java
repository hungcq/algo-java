package recusion_dynamic;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: HungCQ
 * Date: 28-Jul-20
 */
public class PowerSet {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        PowerSet powerSet = new PowerSet();
        List<List<Integer>> power = powerSet.powerSet(new int[]{1, 4, 6, 62, 4, 21, 5});
        System.out.println(gson.toJson(power));
        System.out.println(power.size());
    }

    List<List<Integer>> powerSet(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, array, new ArrayList<>(), result);
        return result;
    }

    void backtrack(int i, int[] array, List<Integer> list, List<List<Integer>> result) {
        if (i == array.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        list.add(array[i]);
        backtrack(i + 1, array, list, result);
        list.remove(list.size() - 1);
        backtrack(i + 1, array, list, result);
    }
}
