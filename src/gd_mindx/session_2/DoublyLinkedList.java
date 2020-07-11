package gd_mindx.session_2;

/**
 * Created by: HungCQ
 * Date: 21-May-20
 */
public class DoublyLinkedList {

    Node head;
    Node tail;

    class Node {
        int value;
        Node next;
        Node prev;
    }

    public DoublyLinkedList() {

    }

    public int size() {
        if (head == null) {
            return 0;
        }
        int count = 1;
        Node node = head;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int valueAt(int index) {
        if (head == null) {
            return -1;
        }
        int i = 0;
        Node node = head;
        while (node != null) {
            if (i == index) {
                return node.value;
            }
            node = node.next;
            i++;
        }
        return -1;
    }

    public void pushFront(int value) {
        Node newNode = new Node();
        newNode.value = value;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public int popFront() {
        if (head == null) {
            return -1;
        }
        Node tmp = head;
        head = head.next;
        head.prev = null;
        return tmp.value;
    }

    public void pushBack(int value) {
        Node node = new Node();
        node.value = value;
        if (tail == null) {
            tail = head = node;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public int popBack() {
        if (tail == null) {
            return -1;
        }
        Node tmp = tail;
        tail = tail.prev;
        tail.next = null;
        return tmp.value;
    }

    public int front() {
        if (head == null) {
            return -1;
        }
        return head.value;
    }

    public int back() {
        if (tail == null) {
            return -1;
        }
        return tail.value;
    }

    public boolean insert(int value, int index) {
        if (index == 0) {
            pushFront(value);
            return true;
        }
        Node newNode = new Node();
        newNode.value = value;
        int i = 0;
        Node node = head;
        while (node != null) {
            if (i == index) {
                Node tmp = node.prev;
                newNode.next = node;
                node.prev = newNode;
                newNode.prev = tmp;
                if (tmp != null) {
                    tmp.next = newNode;
                }
                return true;
            }
            i++;
            node = node.next;
        }
        if (i == index) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            return true;
        }
        return false;
    }

    public void removeAt(int index) {
        if (head == null) {
            return;
        }
        if (index == 0) {
            popFront();
            return;
        }
        Node node = head;
        int i = 0;
        while (node != null) {
            if (i == index) {
                Node next = node.next;
                Node prev = node.prev;
                if (next != null) {
                    next.prev = prev;
                }
                if (prev != null) {
                    prev.next = next;
                }
                if (node == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                }
                break;
            }
            node = node.next;
            i++;
        }
    }

    public void print() {
        if (head == null) {
            System.out.println("empty");
            return;
        }
        Node node = head;
        while (node != null) {
            System.out.print(node.value + ";");
            node = node.next;
        }
        System.out.print("\n");
    }
}
