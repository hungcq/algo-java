package gd_mindx.session_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by: HungCQ
 * Date: 18-Jun-20
 */
public class Test {

    ////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        try {

            double value;
            Tree24 theTree = new Tree24();

            theTree.insert(50);
            theTree.insert(40);
            theTree.insert(60);
            theTree.insert(30);
            theTree.insert(70);

            while (true) {
                putText("Enter first letter of ");
                putText("show, insert, or find: ");
                char choice = getChar();
                switch (choice) {
                    case 's':
                        theTree.displayTree();
                        break;
                    case 'i':
                        putText("Enter value to insert: ");
                        value = getInt();
                        theTree.insert(value);
                        break;
                    case 'f':
                        putText("Enter value to find: ");
                        value = getInt();
                        int found = theTree.find(value);
                        if (found != -1)
                            System.out.println("Found " + value);
                        else
                            System.out.println("Could not find " + value);
                        break;
                    default:
                        putText("Invalid entry\n");
                }  // end switch
            }  // end while
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  // end main()

    //--------------------------------------------------------------
    public static void putText(String s) {
        System.out.print(s);
        System.out.flush();
    }

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //--------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
