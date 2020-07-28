package recusion_dynamic;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.List;

public class PermutationTest {
    private static final Gson gson = new Gson();
    @Test
    public void permutation() {
        int[] array = new int[]{1,2,3,2,9,9,9};
        List<List<Integer>> permutation = Permutation.permute(array);
//        System.out.println(gson.toJson(permutation));
        System.out.println(permutation.size());
    }
}