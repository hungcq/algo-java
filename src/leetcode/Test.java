package leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Test {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] nums = input.split(" ");
        int numCommands = Integer.parseInt(nums[0]);
        int capacity = Integer.parseInt(nums[1]);
        LimitedSizeQueue queue = new LimitedSizeQueue(capacity);
        for (int i = 0; i < numCommands; i++) {
            String command = br.readLine();
            switch (command) {
                case "TAKE":
                    String element = queue.take();
                    if (element != null) {
                        System.out.println(element);
                    }
                    break;
                case "SIZE":
                    System.out.println(queue.size());
                    break;
                default:
                    if (!command.contains("OFFER")) {
                        break;
                    }
                    String[] components = command.split(" ");
                    if (components.length < 2) {
                        break;
                    }
                    String str = components[1];
                    boolean result = queue.offer(str);
                    System.out.println(result);
            }
        }
    }

    static class LimitedSizeQueue {

        int capacity;
        LinkedList<String> linkedList = new LinkedList<>();

        LimitedSizeQueue(int capacity) {
            this.capacity = capacity;
        }

        int size() {
            return linkedList.size();
        }

        String take() {
            if (linkedList.size() == 0) {
                return null;
            }
            return linkedList.removeFirst();
        }

        boolean offer(String str) {
            if (linkedList.size() == capacity) {
                return false;
            }
            linkedList.add(str);
            return true;
        }
    }
}
