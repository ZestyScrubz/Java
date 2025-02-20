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
        // System.out.println("car" + car);
        DoubleNode<TrainCar> newNode = new DoubleNode<>(car);

        // If train is empty new car is locomotive and caboose
        if (locomotive == null) {
            locomotive = newNode;
            caboose = newNode;
        } else {
            // add the new car to the end of the train

            if (caboose.getElement().canConnect(car)) {
                newNode.setNext(caboose);
                caboose.setPrevious(newNode);
                caboose = newNode;
                
            } else {
                
                DoubleNode<TrainCar> current = caboose;
                
                while (current != null) {
                    if (current.getElement().canConnect(car) && current.getPrevious().getElement().canConnect(car)) {

                        DoubleNode<TrainCar> previousNode = current.getPrevious();

                        // Make previous node point to the new node and the new node point to the previous
                        previousNode.setNext(newNode);
                        newNode.setPrevious(previousNode);

                        // Make new node point to next node/current node and next node point to new node
                        newNode.setNext(current);
                        current.setPrevious(newNode);
                        break;
                        
                    } else {
                        current = current.getNext();
                    }

                }

                throw new TrainException("The car could not be added");
            }
        }

    }

    public boolean tryAddCar(TrainCar car) {
        try {
            addCar(car);
            return true;
        } catch (TrainException e) {
            return false;
        }
    }

    public void removeCar(TrainCar car) {
        // Start from the back of the train and check if car are the same
        DoubleNode<TrainCar> current = caboose;
        while(current != null) {
            System.out.println(car);
            if (current.getElement().equals(car)) {
                // if its the only car in the train
                if (current == locomotive && current == caboose) {
                    locomotive = null;
                    caboose = null;
                } else if (current == caboose) {
                    // if it's the caboose
                    caboose = current.getNext();
                    caboose.setPrevious(null);
                } else if (current == locomotive) {
                    // if it's the locomotive
                    locomotive = current.getPrevious();
                    locomotive.setNext(null);
                } else {
                    // if the car is in the middle of the train
                    DoubleNode<TrainCar> prevNode = current.getPrevious();
                    DoubleNode<TrainCar> nextNode = current.getNext();

                    // check if the previous and next car can be connected
                    if (prevNode.getElement().canConnect(nextNode.getElement())) {
                        prevNode.setNext(nextNode);
                        nextNode.setPrevious(prevNode);
                        break;
                    } else {
                        throw new TrainException("The car could not be removed");
                    }
                }
            } else {
                current = current.getNext();
            }
        }

        throw new TrainException("The car could not be removed");

    }

    public boolean tryRemoveCar(TrainCar car) {
        try{
            removeCar(car);
            return true;
        } catch (TrainException e){
            return false;
        }
    }

    public String toString() {
        String string = "";
    
        if (locomotive == null) {
            return "The train is empty.";
        } else {
            DoubleNode<TrainCar> current = locomotive; // Start from the back
            while (current != null) {
                string += current.getElement().toString(); // Append the current car's string representation
    
                // If this is not the last car, add ", "
                if (current.getPrevious() != null) {
                    string += ", ";
                }
    
                current = current.getPrevious(); // Move to the next car
            }
            return string;
        }
    }
    

}