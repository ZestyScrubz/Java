package Nathan;

public class Compound {

    private Element[] elements;
    private int[] elementCount;
    
    public Compound(PeriodicTable table, String[][] compoundArray) {
        elements = new Element[compoundArray.length];
        elementCount = new int[compoundArray.length];

        for (int i = 0; i < compoundArray.length; i++) {
            int count = 1; // default element count is 1
            String symbol = compoundArray[i][0];

            // if length of the array is 2, that means it has a value different from the default 
            if (compoundArray[i].length == 2) {
                count = Integer.parseInt(compoundArray[i][1]);
            }

            // using the symbol, get the object from the periodic table class 
            Element element = table.getElement(symbol);

            // store the element and count into the previously declared arrays
            elements[i] = element;
            elementCount[i] = count;
        }

    }

    /**
     * find what bond type the two elements create
     * @return bond type or null
     */
    public String getBondType() {


        if (elements.length != 2) {
            return null;
        } else {
            
            String type1 = elements[0].getType().toLowerCase();
            String type2 = elements[1].getType().toLowerCase();

            if ((type1.equals("metal") && type2.equals("nonmetal")) || (type2.equals("metal") && type1.equals("nonmetal"))) {
                return "ionic";
            } else if ((type1.equals("metal") && type2.equals("metalloid")) || (type2.equals("metal") && type1.equals("metalloid"))) {
                return "covalent";
            } else if ((type1.equals("nonmetal") && type2.equals("nonmetal"))) {
                return "covalent";
            } else return null;
        }
    }

    /**
     * creates a string with the element name and counts
     * @return string with the element name and count
     */
    public String toString() {
        String elementName = "";
        int elementNum = 0;
        String elementResult = "";
        
        for (int i = 0; i < elements.length; i++) {
            elementName = elements[i].getName();
            elementNum = elementCount[i];
            elementResult += elementName + ": " + elementNum;
        }
        
        return elementResult;
    }
}