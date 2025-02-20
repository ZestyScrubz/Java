public class Train {

    private DoubleNode<TrainCar> locomotive;
    private DoubleNode<TrainCar> caboose;

    public Train() {
        locomotive = null;
        caboose = null;
    }

    public DoubleNode<TrainCar> getLocomotive() {
        return locomotive;
    }

    public DoubleNode<TrainCar> getCaboose() {
        return caboose;
    }

    public void addCar(TrainCar car) {
        DoubleNode<TrainCar> newNode = new DouleNode<>(car);

        // If train is empty new car is locomotive and caboose
        if (locomotive == null) {
            locomotive = newNode;
            caboose = newNode;
        } else {
            // Check if the new car can connect to the end of the train
            if (caboose.getData().canConnect(car)) {
                // Add it to the end
                caboose.setNext(newNode); // Link current cabbose to the new node
                newNode.setPrev(caboose); // Link new node back to current caboose
                caboose = newNode; // Update caboose to the new node
            } else {
                // if it cannot be added to the back
                DoubleNode<TrainCar> current = caboose;

                while (current != null) {
                    if (current.getData().canConnect(car)) {
                        DoubleNode<TrainCar> nextNode = current.getNext();

                        // Link new car after current
                        current.setNext(newNode);
                        newNode.setPrev(current);

                        if (nextNode != null) {
                            // Link new car to the next node if it exists
                            newNode.setNext(nextNode);
                            nextNode.setPrev(newNode);
                        } else {
                            caboose = newNode;
                        }
                    }

                    current = current.getPrev();
                }

                throw new TrainException("The car could not be added in the train");

            }
        }
    }

    public boolean tryAddCar(TrainCar car) {
        try {
            addCar(car);
            return true;
        } catch (TrainException) {
            return false;
        }
    }

    public void removeCar(TrainCar car) {
        // Start from the back of the train and check if car are the same
        DoubleNode<TrainCar> current = caboose;

        while(current != null) {
            if (current.getData().equals(car)) {
                // if its the only car in the train
                if (current == locomotive && current = caboose) {
                    locomotive = null;
                    caboose = null;
                } else if (current == caboose) {
                    // if it's the caboose
                    caboose = current.getnext();
                    caboose.setPrev(null);
                } else if (current == locomotive) {
                    // if it's the locomotive
                    locomotive = current.getPrev();
                    locomotive.setNext(null)
                } else {
                    // if the car is in the middle of the train
                    DoubleNode<TrainCar> prevNode = current.getPrev();
                    DoubleNode<TrainCar> nextNode = current.getNext();

                    // check if the previous and next car can be connected
                    if (prevNode.getData().canConnect(nextNode.getData())) {
                        prevNode.setNext(nextNode);
                        nextNode.setPrev(prevNode);
                    }
                }
            } else {
                throw new TrainException("The car could not be removed");
            }
        }

        throw new TrainException("The car could not be removed");

    }

    public boolean tryRemoveCar(TrainCar car) {
        try{
            removeCar(car);
            return true;
        } catch TrainException {
            return false;
        }
    }

    public String toString() {

        string = "";

        if (locomotive == null && cabbose == null) {
            return "The train is empty.";
        } else {
            DoubleNode<TrainCar> current = caboose; // start from the back

            while (current != null) {
                string += current.getData().toString();

                if (current.getNext() != null) {
                    string += ", "
                }

                current = current.getNext()
            }

            return stirng;
        }
    }

}