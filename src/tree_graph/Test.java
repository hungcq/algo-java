package tree_graph;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by: HungCQ
 * Date: 09-Jul-20
 */
public class Test {
    public static void main(String[] args) {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(10000);
        }
        Arrays.sort(array);
        Node root = buildBstFromSortedArray(array, 0, array.length - 1);
        System.out.println(getHeight(root));
        inorder(root);
    }

    static Node buildBstFromSortedArray(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        Node root = new Node(array[mid]);
        root.left = buildBstFromSortedArray(array, start, mid - 1);
        root.right = buildBstFromSortedArray(array, mid + 1, end);
        return root;
    }

    static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value + " ");
        inorder(root.right);
    }

    static class Node {
        Node left;
        Node right;
        int value;

        Node (int value) {
            this.value = value;
        }
    }
}
