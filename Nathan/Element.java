package Nathan;

public class Element {

    private int atomicNo;
    private float atomicWeight;
    private String symbol;
    private String name;
    private String state;
    private String type;

    public Element(int num, float wt, String sym, String nm, String st, String ty) {
        atomicNo = num;
        atomicWeight = wt;
        symbol = sym;
        name = nm;
        state = st;
        type = ty;
    }

    // getter method
    public int getAtomicNo() {
        return atomicNo;
    }

    // getter method
    public float getAtomicWeight() {
        return atomicWeight;
    }

    // getter method
    public String getSymbol() {
        return symbol;
    }

    // getter method
    public String getName() {
        return name;
    }

    // getter method
    public String getState() {
        return state;
    }

    // getter method
    public String getType() {
        return type;
    }

    // setter method
    public void setName(String name) {
        this.name = name;
    }

    // setter method
    public void setState(String state) {
        this.state = state;
    }

    // setter method
    public void setType(String type) {
        this.type = type;
    }

    // returns a string format
    public String toString() {
        return String.format("%s (%s)", symbol, name);
    }

    public boolean equals(Element other) {
        if (this.atomicNo == other.atomicNo)
            return true;
        else
            return false;
    }
}