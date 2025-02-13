public class test {
    public static void main(String[] args) {

        Integer[][] table = new Integer[3][4];

        Integer[] tester = new Integer[(table[2].length)];
        int indexPos = 0;

        System.out.println(table.length);

        table[0][0] = 1;
        table[1][0] = 2;
        table[2][0] = 3;

        System.out.println(table);

        tester[0] = table[2][1];
        System.out.println(table[2][1]);
        System.out.println(tester[0]);
    }
}
