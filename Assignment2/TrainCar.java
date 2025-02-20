public class TrainCar {

    private int weight;
    private String freight;
	
	public TrainCar(int weight, String freight) {
        this.weight = weight;
        this.freight = freight;
	}

    public int getWeight() {
        return weight;
    }

    public String getFreight() {
        return freight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String toString() {
        return String.format("<%s,%d >", freight, weight);
    }

    public boolean canConnect(TrainCar other) {
        return (
            this.getWeight() == other.getWeight() || this.getFreight().equals(other.getFreight())
        );
    }

    public boolean equals(TrainCar other) {
        return (
            this.getWeight() == other.getWeight() && this.getFreight().equals(other.getFreight())
        );
    }
}