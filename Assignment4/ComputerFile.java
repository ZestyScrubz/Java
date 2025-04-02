/*
CS 1027B – Assignment 4
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: March 28, 2025
*/

public class ComputerFile extends FileSystemObject {

    // Global variable for the file size
    private int size;

    /**
     * Constructor intialize name, id and size of a file
     * @param name of the file
     * @param id number of the file
     * @param size of the file
     */
    public ComputerFile(String name, int id, int size) {
        super(name, id);
        this.size = size;
    }

    /***
     * Get the size of the file
     */
    public int size() {
        return this.size;
    }
}
