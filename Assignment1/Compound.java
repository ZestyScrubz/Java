/*
CS 1027B – Assignment 1
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: January 27, 2025
*/

public class Compound {
    // Array to store elements in the compound
    private Element[] elements;
    // Array to store the count of each element in the compound
    private int[] elementCount;

    /**
     * Constructor for the Compound class.
     * Takes a PeriodicTable and a 2D array representing the compound composition.
     *
     * @param table         The PeriodicTable object to retrieve elements from.
     * @param compoundArray A 2D array where each row contains an element symbol
     *                      and optionally a count.
     */
    public Compound(PeriodicTable table, String[][] compoundArray) {
        // Initialize the arrays based on the number of elements in the compound
        elements = new Element[compoundArray.length];
        elementCount = new int[compoundArray.length];

        // Loop through the compoundArray to extract element symbols and counts
        for (int i = 0; i < compoundArray.length; i++) {
            int count = 1; // Default count is 1 if no count is provided
            String symbol = compoundArray[i][0]; // Get the element symbol

            // Check if there is a count specified in the array
            if (compoundArray[i].length > 1) {
                count = Integer.parseInt(compoundArray[i][1]); // Convert count to an integer
            }

            // Retrieve the Element object from the PeriodicTable using the symbol
            Element element = table.getElement(symbol);

            // Store the retrieved Element and its count in the respective arrays
            elements[i] = element;
            elementCount[i] = count;
        }
    }

    /**
     * Determines the bond type of the compound.
     * Only works for binary compounds (compounds with exactly two elements).
     *
     * @return A string indicating the bond type ("ionic" or "covalent"),
     *         or null if the bond type cannot be determined.
     */
    public String getBondType() {
        // Check if the compound contains exactly two elements
        if (elements.length == 2) {

            String type1 = elements[0].getType(); // Get the type of the first element
            String type2 = elements[1].getType(); // Get the type of the second element
            
            // If one element is a metal and the other is a nonmetal, the bond is ionic
            if ((type1.equals("Metal") && type2.equals("Nonmetal")) ||
                    (type1.equals("Nonmetal") && type2.equals("Metal"))) {
                return "ionic";
            }
            // If one element is a metal and the other is a metalloid, the bond is covalent
            else if ((type1.equals("Metal") && type2.equals("Metalloid")) ||
                    (type1.equals("Metalloid") && type2.equals("Metal"))) {
                return "covalent";
            }
            // If both elements are nonmetals, the bond is covalent
            else if (type1.equals("Nonmetal") && type2.equals("Nonmetal")) {
                return "covalent";
            }
            // If none of the conditions match, return null
            else {
                return null;
            }
        } else {
            return null; // Return null if the compound has more than two elements
        }
    }

    /**
     * Returns a string representation of the compound, listing each element and its count.
     *
     * @return A formatted string representing the compound's composition.
     */
    public String toString() {
        String result = "";

        // Loop through all elements in the compound
        for (int i = 0; i < elements.length; i++) {
            result += elements[i].getName() + ": " + elementCount[i] + "\n";
        }

        return result; // Return the formatted string
    }
}
