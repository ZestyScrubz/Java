package Nathan;

 public class PeriodicTable {

    private Element[][] mainTable;
    private Element[] lanthanides;
    private Element[] actinides;

    public PeriodicTable (String fileName) {
        mainTable = new Element[7][18];
        lanthanides = new Element[15];
        actinides = new Element[15];
        loadData(fileName);

    }

    /**
     * this method uses and reads a file and populates the arrays declared previously with element objects
     * @param filename text file name
     */
    public void loadData(String filename) {
        TextFileReader fr = new TextFileReader(filename);

        // remove the first line
        if (!fr.endOfFile()) {
            fr.readString(); 
        }

        String name, sym, st, ty;
        int atoN;
        float atoW;

        while (!fr.endOfFile()) {
            String line = fr.readString();
            String[] data = line.split(",");

            atoN = Integer.parseInt(data[0]);
            atoW = Float.parseFloat(data[3]);
            name = data[1];
            sym = data[2];
            st = data[9];
            
            // find which type the element is metal, non-metal, metalloid
            if (data[12].equals("yes")) {
                ty = "Metal";
            } else if (data[13].equals("yes")) {
                ty = "Nonmetal";
            } else {
                ty = "Metalloid";
            }

            Element element = new Element(atoN, atoW, sym, name, st, ty);

            // if the type of element is equal to lanthanide or actinide then add to the array
            if ((data[15].toLowerCase()).equals("lanthanide")) {
                lanthanides[atoN - 57] = element; 
            } else if ((data[15].toLowerCase()).equals("actinide")) {
                actinides[atoN - 89] = element; 
            } else {
                int row = 0; 
                int col = 0;

                // find the row and column of each element with the atomic number without adding the white space
                if (atoN == 1) {
                    row = 0;
                    col = 0;
                } else if (atoN == 2) {
                    row = 0;
                    col = 17;
                } else if (atoN >= 3 && atoN <= 10) {
                    row = 1;
                    if (atoN >= 3 && atoN <= 4) {
                        col = atoN - 3;
                    } else {
                        col = atoN + 7;
                    }
                } else if (atoN >= 11 && atoN <= 18) {
                    row = 2;
                    if (atoN >= 11 && atoN <= 12) {
                        col = atoN - 11;
                    } else {
                        col = atoN - 1;
                    }
                } else if (atoN >= 19 && atoN <= 36) {
                    row = 3;
                    col = atoN - 19;
                } else if (atoN >= 37 && atoN <= 54) {
                    row = 4;
                    col = atoN - 37;
                } else if (atoN >= 55 && atoN <= 56) {
                    row = 5;
                    col = atoN - 55;
                } else if (atoN >= 72 && atoN <= 86) {
                    row = 5;
                    col = atoN - 69;
                } else if (atoN >= 87 && atoN <= 88) {
                    row = 6;
                    col = atoN - 87;
                } else if (atoN >= 104 && atoN <= 118) {
                    row = 6;
                    col = atoN - 101;
                }

                // add the element to the corresponding place in the 2D array
                mainTable[row][col] = element;

            }
        }
    }

    /**
     * creates a periodic table using the 3 arrays
     * 
     * @return a periodic table with appropriate spaces 
     */
    public String toString() {
        String table = "";

        // add the elements for the main table into a string and skip the null values 
        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                if (mainTable[i][j] != null) {
                    table += String.format("%4s", mainTable[i][j].getSymbol());
                } else table += "    "; 
            }
            table += "\n"; // next line for every row
        }

        // add the elements for the lanthanides into a string
        table += "\n            "; // next line and 12 spaces
        for (int i = 0; i < lanthanides.length; i++) {
            table += String.format("%4s", lanthanides[i].getSymbol());
        }

        // add the elements for the actinides into a string
        table += "\n            "; // next line and 12 spaces
        for (int i = 0; i < actinides.length; i++) {
            table += String.format("%4s", actinides[i].getSymbol());
        }

        return table;
    }


    /**
     * method that returns the correct element corresponding to the symbol given
     * @param sym element symbol
     * @return element object
     */
    public Element getElement(String sym) {

        for (int i = 0; i < mainTable.length; i++) {
            for (int j = 0; j < mainTable[i].length; j++) {
                if (mainTable[i][j] != null && mainTable[i][j].getSymbol().equals(sym)) return mainTable[i][j];
            } 
        }

        for (int i = 0; i < lanthanides.length; i++) {
            if (lanthanides[i] != null && lanthanides[i].getSymbol().equals(sym)) return lanthanides[i];
        }

        for (int i = 0; i < actinides.length; i++) {
            if (actinides[i] != null && actinides[i].getSymbol().equals(sym)) return actinides[i];
        }

        return null;
    }

    /**
     * method that finds an array of elements of the corresponding period
     * @param per row of the periodic table
     * @return array of elements
     */
    public Element[] getPeriod(int per) {
        if (per <= 0 || per >= 8) return null;

        int arrLen = 0;
        
        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[per-1][i] != null) arrLen++;
        }

        Element[] periodElements = new Element[arrLen];
        int index = 0;

        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[per-1][i] != null) {
                periodElements[index] = mainTable[per-1][i];
                index++;
            }
        }
        return periodElements;
    }

    /**
     *  method that finds an array of elements of the corresponding group
     * @param grp column of the periodic table
     * @return array of elements
     */
    public Element[] getGroup(int grp) {
        if (grp <= 0 || grp >= 19) return null;

        int arrLen = 0;
        
        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[i][grp-1] != null) arrLen++;
        }

        Element[] groupElements = new Element[arrLen];
        int index = 0;

        for (int i = 0; i < mainTable.length; i++) {
            if (mainTable[i][grp-1] != null) {
                groupElements[index] = mainTable[i][grp-1];
                index++;
            }
        }
        return groupElements;

    }

    // getter method
    public Element[] getLanthanides() {
        return lanthanides;
    }

    // getter method
    public Element[] getActinides() {
        return actinides;
    }

}
