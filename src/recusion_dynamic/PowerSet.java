package recusion_dynamic;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<List<Integer>> powerSetWithoutDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        subsetsWithDupHelper(nums, 0, res, new ArrayList<>());
        return res;
    }

    private void subsetsWithDupHelper(int[] nums, int pos, List<List<Integer>> res, List<Integer> tmpRes) {
        // subset means it does not need contain all elements, so the condition is <= rather than ==
        // and do not return after this statement
        if(pos <= nums.length) res.add(new ArrayList<>(tmpRes));

        for(int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i-1]) {
                continue;   // avoid duplicates
            }
            tmpRes.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, res, tmpRes);
            tmpRes.remove(tmpRes.size() - 1);
        }
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
