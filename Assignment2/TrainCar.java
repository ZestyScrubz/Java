/*
CS 1027B – Assignment 2
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: February 18, 2025
*/

public class TrainCar {

    // Global variable instances
    private int weight;
    private String freight;
	
    /*
     * Constructor to initialize variable
     * 
     * @param weight Weight of the carried item
     * @param freight Name of the carried item
     */
	public TrainCar(int weight, String freight) {
        this.weight = weight;
        this.freight = freight;
	}

    // Getter methods to retreive weight and freight of a TrainCar
    public int getWeight() {
        return weight;
    }

    public String getFreight() {
        return freight;
    }

    // Setter methods to set weight and freight of a TrainCara
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    /*
     * Return a string representing this element in the format: <freight, weight>
     * 
     * @return A string representing this element in the format: <freight, weight>
     */
    public String toString() {
        return String.format("<%s, %d>", freight, weight);
    }

    /*
     * Determine whether or not this TrainCar can connect to other
     * 
     * @return true if this and other can connect, false if they cannot
     */
    public boolean canConnect(TrainCar other) {
        return (
            this.getWeight() == other.getWeight() || this.getFreight().equals(other.getFreight())
        );
    }

    /*
     * Determine if this and other are considered to be identical.
     * 
     * @return true if this and other are identical, false if they are not
     */
    public boolean equals(TrainCar other) {
        // If one is a Reefer and the other is not, they are not equal
        if ((this instanceof Reefer && !(other instanceof Reefer)) || (!(this instanceof Reefer) && other instanceof Reefer)) {
                return false;
        } else {
            return (this.getWeight() == other.getWeight() && this.getFreight().equals(other.getFreight()));
        }
    }
}