package balanced_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AvlTree {

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        for (int i = 1; i <= 10000000; i++) {
            tree.insert(i);
        }
        for (int i = 1; i <= 9999000; i++) {
            tree.delete(i);
        }
        System.out.println(tree.isBalance(tree.root).isBalance);
        tree.delete(1000000000);
        System.out.println(tree.isBalance(tree.root).isBalance);
        System.out.println(tree.getMaxHeight(tree.root));
        List<List<Integer>> result = levelOrder(tree.root);
        int totalNode = 0;
        for (List<Integer> list : result) {
            totalNode += list.size();
        }
        System.out.println(totalNode);
        System.out.println(result.size());
    }

    private class Node {
        int value;
        int height;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            height = 1;
        }
    }

    private Node root;

    public AvlTree() {
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }
        return balanceNode(root);
    }

    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value > value) {
            root.left = delete(root.left, value);
        } else if (root.value < value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left != null && root.right != null) {
                Node tmp = root.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                root.value = tmp.value;
                root.right = delete(root.right, tmp.value);
            }
            if (root.left == null) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return balanceNode(root);
    }

    private Node balanceNode(Node root) {
        root.height = getParentHeight(root);
        if (getBalanceFactor(root) > 1) {
            if (getBalanceFactor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            root = rotateRight(root);
        } else if (getBalanceFactor(root) < -1) {
            if (getBalanceFactor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            root = rotateLeft(root);
        }
        return root;
    }

    class Res {
        int height;
        boolean isBalance;
        Res (int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public Res isBalance(Node root) {
        if (root == null) {
            return new Res(0, true);
        }
        Res leftRes = isBalance(root.left);
        Res rightRes = isBalance(root.right);
        int height = 1 + Math.max(leftRes.height, rightRes.height);
        boolean isBalance = Math.abs(leftRes.height - rightRes.height) <= 1 && leftRes.isBalance && rightRes.isBalance;
        Res rootRes = new Res(height, isBalance);
        return rootRes;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        Node tmp = left.right;
        left.right = node;
        node.left = tmp;
        node.height = getParentHeight(node);
        left.height = getParentHeight(left);
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        Node tmp = right.left;
        right.left = node;
        node.right = tmp;
        node.height = getParentHeight(node);
        right.height = getParentHeight(right);
        return right;
    }

    private int getParentHeight(Node parent) {
        return Math.max(getHeight(parent.left), getHeight(parent.right)) + 1;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.value);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public int getMaxHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getMaxHeight(node.left), getMaxHeight(node.right)) + 1;
    }
}
