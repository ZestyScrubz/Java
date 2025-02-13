/*
CS 1027B – Assignment 1
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: January 27, 2025
*/

public class Element {

    // Instance variables representing properties of an element
    private int atomicNo;         // Atomic number of the element
    private float atomicWeight;   // Atomic weight of the element
    private String symbol;        // Chemical symbol of the element
    private String name;          // Name of the element
    private String state;         // Physical state (solid, liquid, gas)
    private String type;          // Type of element (metal, non-metal, etc.)

    /**
     * Constructor to initialize an Element object with given properties.
     * @param num Atomic number
     * @param wt Atomic weight
     * @param sym Chemical symbol
     * @param nm Element name
     * @param st Physical state
     * @param ty Element type
     */
    public Element(int num, float wt, String sym, String nm, String st, String ty) {
        atomicNo = num;
        atomicWeight = wt;
        symbol = sym;
        name = nm;
        state = st;
        type = ty;
    }

    // Getter methods for retrieving element properties
    public int getAtomicNo() {
        return atomicNo;
    }

    public float getAtomicWeight() {
        return atomicWeight;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    // Setter methods for modifying certain properties of an element
    public void setName(String nm) {
        name = nm;
    }

    public void setState(String st) {
        state = st;
    }

    public void setType(String ty) {
        type = ty;
    }

    /**
     * Returns a string representation of the element.
     * @return Symbol and name formatted as "Symbol (Name)"
     */
    public String toString() {
        return (symbol + " (" + name + ")");
    }

    /**
     * Compares this element with another to check if they are the same.
     * @param other Another Element object
     * @return true if atomic numbers match, otherwise false
     */
    public boolean equals(Element other) {
        return this.atomicNo == other.atomicNo;
    }
}
