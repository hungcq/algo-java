package gd_mindx.session_2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: HungCQ
 * Date: 25-May-20
 */
public class PairNumber {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 4, 4, 5, 6, 7};
        int[] arr2 = new int[]{0, 3, 3, 4, 4, 5, 6, 6, 8};
        List<int[]> result = pairNumber(arr1, arr2, 10);
        System.out.println(gson.toJson(result));
    }

    public static List<int[]> pairNumber(int[] arr1, int[] arr2, int sum) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int j = arr2.length - 1;
        while (i < arr1.length && j >= 0) {
            if (arr1[i] + arr2[j] == sum) {
                result.add(new int[]{arr1[i], arr2[j]});
                i++;
                j--;
            } else if (arr1[i] + arr2[j] > sum) {
                j--;
            } else if (arr1[i] + arr2[j] < sum) {
                i++;
            }
        }
        return result;
    }
}
