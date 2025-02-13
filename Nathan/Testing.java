public class Testing {
    public static void main(String[] args) {
        int[] array = new int[5];
        int k = 5;

        while (k > 0) {
            array[--k] = k;
            System.out.println(k);
        }
    }
}
