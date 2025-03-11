/*
CS 1027B – Assignment 2
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: February 18, 2025
*/

public class Train {

    // Global intances
    private DoubleNode<TrainCar> locomotive; // Front of the train
    private DoubleNode<TrainCar> caboose; // Back of the train

    /*
     * Constructor to initialize global variable
     */
    public Train() {
        locomotive = null;
        caboose = null;
    }

    // Getter methods to retreive locomotive and caboose
    public DoubleNode<TrainCar> getLocomotive() {
        return locomotive;
    }

    public DoubleNode<TrainCar> getCaboose() {
        return caboose;
    }

    /*
     * Add a new car to the train
     * 
     * @param car New car to be added to the train
     * @throw TrainException if the car could not be added to the train
     */
    public void addCar(TrainCar car) {
        DoubleNode<TrainCar> newNode = new DoubleNode<>(car);

        // If train is empty, the new car is locomotive and caboose
        if (locomotive == null) {
            locomotive = newNode;
            caboose = newNode;
        } else {

            // Check to see if the new car can connect to the caboose
            if (caboose.getElement().canConnect(car)) {
                // Make new car the caboose
                caboose.setNext(newNode);
                newNode.setPrevious(caboose);
                caboose = newNode;
                
            } else {
                DoubleNode<TrainCar> current = caboose;
                while (current != null) {

                    // If the current car AND the previous car can connect to the new car
                    // Put the new car between the two cars
                    if (current.getElement().canConnect(car) && current.getNext().getElement().canConnect(car)) {
                        
                        DoubleNode<TrainCar> nextNode = current.getNext();

                        // Make previous node point to the new node and the new node point to the previous
                        newNode.setNext(nextNode);
                        nextNode.setPrevious(newNode);
                        

                        // Make new node point to next node/current node and next node point to new node
                        current.setNext(newNode);
                        newNode.setPrevious(current);
                        
                        return;
                        
                    } else {
                        current = current.getPrevious();
                    }

                }

                throw new TrainException("The car could not be added");
            }
        }

    }

    /*
     * Attemps to add a TrainCar to the train
     * 
     * @param car The TrainCar object to be added to the train
     * @return true if the TrainCar was successfully added, false if it was not
     */
    public boolean tryAddCar(TrainCar car) {
        try {
            addCar(car);
            return true;
        } catch (TrainException e) {
            return false;
        }
    }

    /*
     * Remove a specified car in the train
     * 
     * @param car The TrainCar object to be removed from the train
     * @throws TrainException if the car cannot be removed due to connection issues
     */
    public void removeCar(TrainCar car) {

        DoubleNode<TrainCar> current = caboose;

        while (current != null) {
            if (current.getElement() != null && current.getElement().equals(car)) {
                DoubleNode<TrainCar> prevNode = current.getPrevious();
                DoubleNode<TrainCar> nextNode = current.getNext();

                if (prevNode == null && nextNode == null) {
                    locomotive = null;
                    caboose = null;
                    return;
                } else if (prevNode == null) {
                    locomotive = nextNode;
                    locomotive.setPrevious(null);
                    return;
                } else if (nextNode == null) {
                    caboose = prevNode;
                    caboose.setNext(null);
                    return;
                } else if (prevNode.getElement().canConnect(nextNode.getElement())) {
                    prevNode.setNext(nextNode);
                    nextNode.setPrevious(prevNode);
                    return;
                } else {
                    throw new TrainException("Removing the car would cause the surrounding cars to be incompatible.");
                }
            } else {
                current = current.getPrevious();
            }
        }

        throw new TrainException("The car does not exist in the train.");
    }

    /*
     * Attempts to remove a specified TrainCar from the trian
     * 
     * @param car The TrainCar object to be removed from the train
     * @return true if the TrainCar was successfully removed, flase if it was not
     */
    public boolean tryRemoveCar(TrainCar car) {
        try {
            removeCar(car);
            return true;
        } catch (TrainException e){
            return false;
        }
    }

    /*
     * Returns a string representation of the train
     * 
     * @return A string representation fo the train
     */
    public String toString() {
        String string = "";
    
        if (locomotive == null) {
            return "The train is empty.";
        } else {
            DoubleNode<TrainCar> current = locomotive; // Start from the locomotive
            while (current != null) {
                string += current.getElement().toString(); // Append the current car's string representation
    
                // If this is not the last car, add ", "
                if (current.getNext() != null) {
                    string += ", ";
                }
    
                current = current.getNext(); // Move to the next car
            }
            return string;
        }
    }
    

}