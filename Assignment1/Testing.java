public class Testing {
    public static void main(String[] args) {
        PeriodicTable table = new PeriodicTable(
                "C:\\Users\\isaac\\OneDrive\\Documents\\GitHub\\Java\\Assignment1\\elements.txt");

        System.out.println(table.getElement("He"));

        // // Compound with Metal and Nonmetal
        // String[][] compoundArray1 = {
        // {"Na", "1"}, // Sodium (Metal)
        // {"O", "1"} // Chlorine (Nonmetal)
        // };

        // Compound compound1 = new Compound(table, compoundArray1);
        // System.out.println("Bond Type 1: " + compound1.getBondType()); // Expected:
        // "ionic"

        // // Compound with Metal and Metalloid
        // String[][] compoundArray2 = {
        // {"Na", "1"}, // Sodium (Metal)
        // {"Si", "1"} // Silicon (Metalloid)
        // };

        // Compound compound2 = new Compound(table, compoundArray2);
        // System.out.println("Bond Type 2: " + compound2.getBondType()); // Expected:
        // "covalent"

        // // Compound with Nonmetals
        // String[][] compoundArray3 = {
        // {"O", "2"}, // Oxygen (Nonmetal)
        // {"H", "2"} // Hydrogen (Nonmetal)
        // };

        // Compound compound3 = new Compound(table, compoundArray3);
        // System.out.println("Bond Type 3: " + compound3.getBondType()); // Expected:
        // "covalent"

        // // Invalid compound (less than 2 elements)
        // String[][] compoundArray4 = {
        // {"H"} // Only one Hydrogen atom
        // };

        // Compound compound4 = new Compound(table, compoundArray4);
        // System.out.println("Bond Type 4: " + compound4.getBondType()); // Expected:
        // null
    }
}
