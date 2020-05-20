import com.google.gson.Gson;
import session_2.Array;

/**
 * Created by: HungCQ
 * Date: 20-May-20
 */
public class Test {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
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
