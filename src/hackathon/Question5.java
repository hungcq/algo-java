package hackathon;

import java.util.ArrayList;
import java.util.List;

public class Question5 {
    // question 5
    int[] findAnagramIndices(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] numCharP = new int['z' - 'a' + 1];
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            numCharP[c - 'a'] += 1;
        }
        int i = 0;
        int j = 0;
        int[] numCharS = new int['z' - 'a' + 1];
        int len = s.length();
        while (j < len) {
            char c = s.charAt(j);
            numCharS[c - 'a'] += 1;
            if (j - i >= p.length()) {
                numCharS[s.charAt(i) - 'a'] -= 1;
                i++;
            }
            if (compareNumChar(numCharP, numCharS)) {
                result.add(i);
            }
            j++;
        }
        int[] resultArray = new int[result.size()];
        for (i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    boolean compareNumChar(int[] numChar1, int[] numChar2) {
        for (int i = 0; i < numChar1.length; i++) {
            if (numChar1[i] != numChar2[i]) {
                return false;
            }
        }
        return true;
    }
}
