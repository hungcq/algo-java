package hackathon;

public class Question3 {
    // question 3
    Hackathon.Node groupOddEvenNodes(Hackathon.Node head) {
        if (head == null) {
            return null;
        }
        Hackathon.Node tail = null;
        Hackathon.Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        tail = node;
        Hackathon.Node end = tail;
        int i = 1;
        node = head;
        Hackathon.Node prev = new Hackathon.Node();
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
}
