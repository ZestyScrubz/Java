/*
CS 1027B – Assignment 1
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: January 27, 2025
*/

public class PeriodicTable {
    // 2D array to store the main periodic table elements
    private Element[][] mainTable;
    // Arrays to store lanthanides and actinides separately
    private Element[] lanthanides;
    private Element[] actinides;

    // Constructor initializes the arrays and loads data from a file
    public PeriodicTable(String filename) {
        mainTable = new Element[7][18];
        lanthanides = new Element[15];
        actinides = new Element[15];

        loadData(filename);
    }

    // Read in the data from elements.txt using TextFileReader
    // and populate the 3 arrays with the correspondin Element objects
    public void loadData(String filename) {

        String name, symbol, state, type;
        int atomicNo;
        float atomicWeight;

        TextFileReader fr = new TextFileReader(filename);

        if (!fr.endOfFile()) {
            fr.readString(); // Discard the first line
        }

        // Read file line by line until EOF
        while (!fr.endOfFile()) {
            String line = fr.readString();
            String[] data = line.split(",");

            // Extract element properties from the parsed data
            atomicNo = Integer.parseInt(data[0]);
            name = data[1];
            symbol = data[2];
            atomicWeight = Float.parseFloat(data[3]);
            state = data[9];

            // Determine element type based on CSV fields
            if (data[12].equals("yes")) {
                type = "Metal";
            } else if (data[13].equals("yes")) {
                type = "Nonmetal";
            } else {
                type = "Metalloid";
            }

            Element element = new Element(atomicNo, atomicWeight, symbol, name, state, type);

            // Populate lanthanides and actinides
            if (atomicNo >= 57 && atomicNo <= 71) {
                lanthanides[atomicNo - 57] = element;
            } else if (atomicNo >= 89 && atomicNo <= 103) {
                actinides[atomicNo - 89] = element;
            } else {
                // Populate main table
                int row = 0;
                int col = 0;

                if (atomicNo == 1) {
                    // H
                    row = 0;
                    col = 0;
                } else if (atomicNo == 2) {
                    // He
                    row = 0;
                    col = 17;
                } else if (atomicNo >= 3 && atomicNo <= 10) {
                    // Li to Ne
                    row = 1;
                    if (atomicNo >= 3 && atomicNo <= 4) {
                        col = atomicNo - 3;
                    } else {
                        col = atomicNo + 7;
                    }
                } else if (atomicNo >= 11 && atomicNo <= 18) {
                    // Na to Ar
                    row = 2;
                    if (atomicNo >= 11 && atomicNo <= 12) {
                        col = atomicNo - 11;
                    } else {
                        col = atomicNo - 1;
                    }
                } else if (atomicNo >= 19 && atomicNo <= 36) {
                    // K to Kr
                    row = 3;
                    col = atomicNo - 19;
                } else if (atomicNo >= 37 && atomicNo <= 54) {
                    // Rb to Xe
                    row = 4;
                    col = atomicNo - 37;
                } else if (atomicNo >= 55 && atomicNo <= 56) {
                    // Cs to Ba
                    row = 5;
                    col = atomicNo - 55;
                } else if (atomicNo >= 72 && atomicNo <= 86) {
                    // Hf to Rn
                    row = 5;
                    col = atomicNo - 69;
                } else if (atomicNo >= 87 && atomicNo <= 88) {
                    // Fr to Ra
                    row = 6;
                    col = atomicNo - 87;
                } else if (atomicNo >= 104 && atomicNo <= 118) {
                    // Rf to Og
                    row = 6;
                    col = atomicNo - 101;
                }

                mainTable[row][col] = element;
            }

        }

        fr.close();
    }

    /**
     * Returns a string representation of the periodic table
     *
     * @return A formatted string representing the periodic table.
     */
    public String toString() {

        String result = "";

        for (int row = 0; row < mainTable.length; row++) {
            for (int col = 0; col < mainTable[row].length; col++) {
                if (mainTable[row][col] == null) {
                    result += ("    ");
                } else {
                    result += String.format("%4s", mainTable[row][col].getSymbol());
                }
            }

            result += "\n"; // Go next row
        }

        result += "\n"; // Blank line between main table and lanthanides/actinides

        // Add lanthinides to the string
        result += ("            "); // Space infront of lanthinides
        for (int sym = 0; sym < lanthanides.length; sym++) {
            result += String.format("%4s", lanthanides[sym].getSymbol());
        }

        result += "\n"; // Go next row

        // Add actinides to string
        result += ("            "); // Spaces infront of actinides
        for (int sym = 0; sym < actinides.length; sym++) {
            if (actinides[sym] == null) {
                result += ("    ");
            } else {
                result += String.format("%4s", actinides[sym].getSymbol());
            }
        }

        return result;
    }

    /**
     * Returns a string representation of the periodic table
     *
     * @param sym Symbol
     * @return A formatted string representing the periodic table.
     */
    public Element getElement(String sym) {

        System.out.println(mainTable[0][1]);

        // Check the main table to see if the element is in there
        for (int row = 0; row < mainTable.length; row++) {
            for (int col = 0; col < mainTable[row].length; col++) {
                // Ensure the element is not null before calling getSymbol()
                if (mainTable[row][col] != null && mainTable[row][col].getSymbol().equals(sym)) {
                    return mainTable[row][col];
                }
            }
        }

        // check lanthanides table
        for (int i = 0; i < lanthanides.length; i++) {
            if (lanthanides[i] != null && lanthanides[i].getSymbol().equals(sym)) {
                return lanthanides[i];
            }
        }

        // check actinides table
        for (int i = 0; i < actinides.length; i++) {
            if (actinides[i] != null && actinides[i].getSymbol().equals(sym)) {
                return actinides[i];
            }
        }

        return null;
    }

    /**
     * Return an array containing the elements from the given period index 
     *
     * @param per Period
     * @return An array containing the elements.
     */
    public Element[] getPeriod(int per) {

        // If period input is less than 1 or more than 7 return null
        if (per <= 0 || per >= 8)
            return null;

        // Get all the count in the period (row) without spaces
        int nonNullCount = 0;
        for (int i = 0; i < mainTable[per].length; i++) {
            if (mainTable[per - 1][i] != null) {
                nonNullCount++;
            }
        }

        Element elementInPeriod[] = new Element[nonNullCount]; // Create a array the length of the # of element in the row
        int indexPos = 0;

        for (int i = 0; i < mainTable[per].length; i++) { // for each column, check if the given row at the curr column its null
            if (mainTable[per - 1][i] != null) { // if it isnt null, add that element to the array
                elementInPeriod[indexPos] = mainTable[per - 1][i];
                indexPos++;
            }
        }

        return elementInPeriod;
    }

    /**
     *  Return an array containing the elements from the given group index
     *
     * @param group Group
     * @return An array containing the elements.
     */
    public Element[] getGroup(int grp) {
        if (grp <= 0 || grp >= 19)
            return null;

        int nonNullCount = 0;
        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[i][grp - 1] != null) {
                nonNullCount++;
            }
        }

        Element elementInGroup[] = new Element[nonNullCount]; // create a array the length of the row
        int indexPos = 0;

        for (int i = 0; i < mainTable.length; i++) { // for each row, check if the curr row is null
            if (mainTable[i][grp - 1] != null) { // if it is null, add that element to the array
                elementInGroup[indexPos] = mainTable[i][grp - 1];
                indexPos++;
            }
        }

        return elementInGroup;
    }

    // Getters for lanthanides and actinides
    public Element[] getLanthanides() {
        return lanthanides;
    }

    public Element[] getActinides() {
        return actinides;
    }

}
