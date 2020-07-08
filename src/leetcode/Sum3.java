package leetcode;

import java.util.*;

public class Sum3 {
    class Solution {
        class Wrapper {
            int num;
            Set<Integer> set = new HashSet<>();
            Wrapper(int num) {
                this.num = num;
            }
        }
        public List<List<Integer>> threeSum(int[] nums) {
            List<Wrapper> set = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    Wrapper wrapper = new Wrapper(nums[i] + nums[j]);
                    wrapper.set.add(i);
                    wrapper.set.add(j);
                    set.add(wrapper);
                }
            }
            Map<Integer, Integer> map = new HashMap<>();
            int index = 0;
            for (int i : nums) {
                map.put(i, index++);
            }
            List<List<Integer>> result = new ArrayList<>();
            Set<String> checkSet = new HashSet<>();
            for (Wrapper wrapper : set) {
                int num = wrapper.num;
                if (map.containsKey(-num) && !wrapper.set.contains(map.get(-num))) {
                    List<Integer> list = new ArrayList<>();
                    list.add(-num);
                    for (int a : wrapper.set) {
                        list.add(nums[a]);
                    }
                    Collections.sort(list);
                    StringBuilder builder = new StringBuilder();
                    for (int a : list) {
                        builder.append(a);
                        builder.append(",");
                    }
                    String check = builder.toString();
                    if (checkSet.contains(check)) {
                        continue;
                    }
                    checkSet.add(check);
                    result.add(list);
                }
            }
            return result;
        }
    }
}
