package hackathon;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hackathon {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(gson.toJson(solution.productOfNums(new int[] {1,2,3,4})));
//        Node start = new Node();
//        start.value = 1;
//        Node tmp = start;
//        for (int i = 2; i <= 10; i++) {
//            Node node = new Node();
//            node.value = i;
//            tmp.next = node;
//            tmp = node;
//        }
//        Node node = solution.groupOddEvenNodes(start);
//        while (node != null) {
//            System.out.println(node.value);
//            node = node.next;
//        }
//        System.out.println(gson.toJson(solution.findAnagramIndices("cbaebabacd","abc")));
//        System.out.println(gson.toJson(solution.findAnagramIndices("abab","ab")));
        System.out.println(solution.decodeString("3[a2[c]]"));
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
    }

    public static class Solution {
        // question 2
        int[] productOfNums(int[] array) {
            int product = 1;
            for (int i : array) {
                product *= i;
            }
            for (int i = 0; i < array.length; i++) {
                array[i] = product / array[i];
            }
            return array;
        }

        // question 3
        Node groupOddEvenNodes(Node head) {
            if (head == null) {
                return null;
            }
            Node tail = null;
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            tail = node;
            Node end = tail;
            int i = 1;
            node = head;
            Node prev = new Node();
            prev.next = node;
            while (node != end) {
                if (i % 2 == 0) {
                    prev.next = node.next;
                    tail.next = node;
                    tail = tail.next;
                    node.next = null;
                    node = prev.next;
                } else {
                    node = node.next;
                    prev = prev.next;
                }
                i++;
            }
            if (i % 2 == 0 && i > 2) {
                prev.next = prev.next.next;
                tail.next = end;
                end.next = null;
            }
            return head;
        }

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

        // question 4
        String decodeString(String s) {
            if (s.length() == 0) {
                return "";
            }
            Stack<String> stack = new Stack<>();
            StringBuilder builder = new StringBuilder();
            boolean isNum = false;
            char firstChar = s.charAt(0);
            if (firstChar >= '1' && firstChar <= '9') {
                isNum = true;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '[' || c == ']') {
                    if (!builder.toString().equals("")) {
                        stack.push(builder.toString());
                    }
                    builder = new StringBuilder();
                } else {
                    if (c >= '1' && c <= '9') {
                        if (!isNum) {
                            if (!builder.toString().equals("")) {
                                stack.push(builder.toString());
                            }
                            builder = new StringBuilder();
                            isNum = true;
                        }
                    } else {
                        isNum = false;
                    }
                    builder.append(c);
                }
            }
            if (!builder.toString().equals("")) {
                stack.push(builder.toString());
            }
            String result = "";
            while (!stack.empty()) {
                String str = stack.pop();
                if (stack.empty()) {
                    result = str + result;
                    return result;
                }
                String next = stack.pop();
                int num = 0;
                try {
                    num = Integer.parseInt(next);
                } catch (NumberFormatException e) {
                    result = str + result;
                    stack.push(next);
                    continue;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < num; i++) {
                    stringBuilder.append(str);
                }
                stack.push(stringBuilder.toString());
            }
            return result;
        }

    }

    public static class Node {
        int value;
        Node next;
    }
}
