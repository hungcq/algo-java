package hackathon;

import java.util.Stack;

public class Question4 {
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
