public class Reefer extends TrainCar {

    private int temp;

    public Reefer(int weight, int temp, String freight) {
        super(weight, freight);
        this.temp = temp;

    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String toString() {
        return (String.format("<%s, %d, %dC>", getFreight(), getWeight(), temp));
    }

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

    public boolean equals(TrainCar other) {
        if (super.equals(other)) {
            return false;
        }

        if (other instanceof Reefer) {
            Reefer otherReefer = (Reefer) other;
            return (this.temp == otherReefer.getTemp());
        }

        return false;

    }

}