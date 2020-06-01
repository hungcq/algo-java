package hackathon_1.session_1;

public class FindDupNum_ChuQuocHung {
    public static void main(String[] args) {
        FindDupNum_ChuQuocHung main = new FindDupNum_ChuQuocHung();
        System.out.println(main.findDupNum(new int[]{3,1,3,4,2}));
        System.out.println(main.findDupNum(new int[]{1,3,4,2,2}));
        System.out.println(main.findDupNum(new int[]{1,3,4,3,3}));
    }

    public int findDupNum(int[] array) {
        int n = array.length - 1;
        long product = 1;
        for (int i : array) {
            product *= i;
        }
        int count = 1;
        for (int i = 1; i <= n; i++) {
            if (product % i == 0) {
                product /= i;
            } else {
                count++;
            }
        }
        System.out.println("product: " + product);
        return (int) Math.round(Math.pow(product, 1.0 / count));
    }
}
