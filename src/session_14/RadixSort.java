package session_14;

public class RadixSort {

    public static void sort(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            max = Math.max(num, max);
        }
        int i = 1;
        while (max != 0) {
            max /= 10;
            countingSort(array, i);
            i *= 10;
        }
    }

    public static void countingSort(int[] array, int i) {
        int[] tmp = new int[10];
        for (int num : array) {
            int digit = (num / i) % 10;
            tmp[digit]++;
        }
        for (int j = 1; j < tmp.length; j++) {
            tmp[j] += tmp[j - 1];
        }
        int[] result = new int[array.length];
        for (int j = array.length - 1; j >= 0; j--) {
            int digit = (array[j] / i) % 10;
            int position = --tmp[digit];
            result[position] = array[j];
        }
        for (int j = 0; j < result.length; j++) {
            array[j] = result[j];
        }
    }
}
