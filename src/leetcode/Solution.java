package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isIsomorphic("ab", "aa"));
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(sc)) {
                if (map.get(sc) != tc) {
                    return false;
                }
            }
            if (map2.containsKey(tc)) {
                if (map.get(tc) != sc) {
                    return false;
                }
            }
            map.put(sc, tc);
            map2.put(tc, sc);
        }
        return true;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Value> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            Value value = new Value();
            value.sumIndex = i;
            map.put(list1[i], value);
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                Value value = map.get(list2[i]);
                value.exist = true;
                value.sumIndex += i;
            }
        }
        int minSum = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Value> entry : map.entrySet()) {
            Value value = entry.getValue();
            if (value.exist) {
                if (value.sumIndex < minSum) {
                    minSum = value.sumIndex;
                }
            }
        }
        for (Map.Entry<String, Value> entry : map.entrySet()) {
            String key = entry.getKey();
            Value value = entry.getValue();
            if (value.exist) {
                if (value.sumIndex == minSum) {
                    result.add(key);
                }
            }
        }
        return result.toArray(new String[0]);
    }

    class Value {
        int sumIndex;
        boolean exist;
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], 0);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                map.put(nums2[i], map.get(nums2[i]) + 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 1) {
                result.add(entry.getKey());
            }
        }
        int[] returnResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            returnResult[i] = result.get(i);
        }
        return returnResult;
    }
}