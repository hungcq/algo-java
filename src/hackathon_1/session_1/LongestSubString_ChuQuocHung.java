package hackathon_1.session_1;

import java.util.HashMap;
import java.util.Map;

public class LongestSubString_ChuQuocHung {

    public static void main(String[] args) {
        LongestSubString_ChuQuocHung main = new LongestSubString_ChuQuocHung();
        System.out.println(main.longestSubString("abcabcbb"));
        System.out.println(main.longestSubString("bbbbb"));
        System.out.println(main.longestSubString("pwwkew"));
    }

    public int longestSubString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int max = 1;
        int len = s.length();
        while (i < len && j < len) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c)) + 1;
            }
            max = Math.max(j - i + 1, max);
            map.put(c, j);
            j++;
        }
        return max;
    }
}
