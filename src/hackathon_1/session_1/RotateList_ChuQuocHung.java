package hackathon_1.session_1;

public class RotateList_ChuQuocHung {

    public static void main(String[] args) {
        Node node1 = new Node();
        node1.value = 0;
        Node start = node1;
        for (int i = 1; i <= 2; i++) {
            Node node = new Node();
            node.value = i;
            node1.next = node;
            node1 = node;
        }
        RotateList_ChuQuocHung main = new RotateList_ChuQuocHung();
        Node a = main.rotateList(start, 4);
        while (a != null) {
            System.out.println(a.value);
            a = a.next;
        }
    }

    public Node rotateList(Node head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = 1;
        Node start = head;
        while (head.next != null) {
            head = head.next;
            len++;
        }
        Node prev = head;
        head.next = start;
        head = start;
        while (k > len) {
            k -= len;
        }
        for (int i = 0; i < len - k; i++) {
            prev = prev.next;
            head = head.next;
        }
        prev.next = null;
        return head;
    }

    public static class Node {
        int value;
        Node next;
    }
}
