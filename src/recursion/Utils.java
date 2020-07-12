package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static List<List<Integer>> removeDuplicate(List<List<Integer>> result) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (List<Integer> list : result) {
            List<List<Integer>> tmp = map.get(list.size());
            if (tmp == null) {
                tmp = new ArrayList<>();
                map.put(list.size(), tmp);
            }
            tmp.add(list);
        }
        List<List<Integer>> tmp = new ArrayList<>();
        for (List<List<Integer>> list : map.values()) {
            tmp.addAll(removeDup(list, 0, list.get(0).size()));
        }
        return tmp;
    }

    private static List<List<Integer>> removeDup(List<List<Integer>> listList, int i, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (i == n) {
            result.add(listList.get(0));
            return result;
        }
        Map<Integer, List<List<Integer>>> map = new HashMap();
        for (List<Integer> list : listList) {
            int num = list.get(i);
            List<List<Integer>> val = map.get(num);
            if (val == null) {
                val = new ArrayList<>();
                map.put(num, val);
            }
            val.add(list);
        }
        for (List<List<Integer>> list : map.values()) {
            if (list.size() == 1) {
                result.addAll(list);
            } else {
                result.addAll(removeDup(list, i + 1, n));
            }
        }
        return result;
    }
}
