package hackathon;

public class Question2 {
    // question 2
    int[] productOfNums(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int product = 1;
        for (int i : array) {
            product *= i;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = product / array[i];
        }
        return array;
    }
}
