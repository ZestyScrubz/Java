/*
CS 1027B – Assignment 2
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: February 18, 2025
*/

public class Reefer extends TrainCar {

    // Global variable instances
    private int temp;

    /*
     * Constructor to initialize variable
     * 
     * @param weight Weight of the carried item
     * @param temp Temperature of the carried item
     * @param freight Name of the carried item
     */
    public Reefer(int weight, int temp, String freight) {
        super(weight, freight);
        this.temp = temp;

    }

    // Getter method to retreive temperature
    public int getTemp() {
        return temp;
    }

    // Setter method to set temperature
    public void setTemp(int temp) {
        this.temp = temp;
    }

    /*
     * Return a string representing this element in the format: <freight, weight, tempC>
     * 
     * @return A string representing this element in the format: <freight, weight, tempC>
     */
    public String toString() {
        return (String.format("<%s, %d, %dC>", getFreight(), getWeight(), temp));
    }

    /*
     * Determine whether or not this Reefer can connect to other
     * 
     * @param other A specified TrainCar to see if it connects with this
     * @return true if they can connect, false otherwise
     */
    public boolean canConnect(TrainCar other) {
        // If the superclass allows connection, return true
        if (super.canConnect(other)) {
            return true;
        }

        // Check if 'other' is a Reefer
        if (other instanceof Reefer) {
            // Compare temperatures, allowing a 5-degree difference
            Reefer otherReefer = (Reefer) other;
            return Math.abs(this.temp - otherReefer.getTemp()) <= 5; // Diffences regardless of order
        }

        // Otherwise, connection isn't allowed
        return false;
    }

    /*
     * Determine if this and other are considered to be identical.
     * 
     * @param other A specified TrainCar to see if it identical to this
     * @return true if they are identical, false otherwise
     */
    public boolean equals(TrainCar other) {
    

        // If they are both non-reefer
        if (!(this instanceof Reefer) && !(other instanceof Reefer)) {
            return (super.equals(other));
        }

        // Check if both are Reefers and compare temperatures
        if (other instanceof Reefer && this instanceof Reefer) {
            if (super.equals(other)) {                
                Reefer otherReefer = (Reefer) other;
                Reefer thisReefer = (Reefer) this;
                return thisReefer.getTemp() == otherReefer.getTemp(); // if the temps are the same
            }
            return false;
        }
    
        // If not both are Reefers, they are not equal
        return false;
    }

}