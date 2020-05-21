package session_2;

import com.google.gson.Gson;

/**
 * Created by: HungCQ
 * Date: 20-May-20
 */
public class Test {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        testLinkedList();
        testArray();
    }

    static void testLinkedList() {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.popBack());
        System.out.println(linkedList.popFront());
        linkedList.pushBack(1);
        linkedList.pushFront(0);
        System.out.println(linkedList.popFront());
        linkedList.print();
        linkedList.pushBack(2);
        linkedList.pushBack(3);
        linkedList.pushBack(4);
        linkedList.pushBack(5);
        linkedList.print();
        System.out.println(linkedList.front());
        System.out.println(linkedList.back());
        System.out.println(linkedList.valueAt(10));
        System.out.println(linkedList.valueAt(0));
        System.out.println(linkedList.valueAt(3));
        System.out.println(linkedList.insert(6, 3));
        linkedList.print();
        System.out.println(linkedList.removeAt(3));
        System.out.println(linkedList.removeAt(-1));
        System.out.println(linkedList.removeAt(10));
        linkedList.print();
    }

    static void testArray() {
        Array array = new Array();
        System.out.println(array.removeAt(1));
        array.append(1);
        System.out.println(array.insert(2, 2));
        System.out.println(array.insert(2, 1));
        System.out.println(array.insert(2, 0));
        System.out.println(array.size());
        System.out.println(array.capacity());
        System.out.println(array.itemAt(1));
        System.out.println(array.itemAt(10));
        System.out.println(array.pop());
        System.out.println(gson.toJson(array));
    }
}
