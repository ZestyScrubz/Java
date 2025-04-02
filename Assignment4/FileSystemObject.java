/*
CS 1027B – Assignment 4
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: March 28, 2025
*/

public class FileSystemObject implements Comparable<FileSystemObject> {

    // Global Variables
    private String name;
    private OrderedListADT<FileSystemObject> children;
    private FileSystemObject parent;
    private int id;

    /**
     * Initialize name and id of the object. If this object is a folder initialize a child
     * If this is a file, initialize its child to be null
     * @param name of the object
     * @param id of the object
     */
    public FileSystemObject(String name, int id) {
        this.name = name;
        this.id = id;

        if (!isFile()) {
            children = new ArrayOrderedList<>();
        } else {
            children = null;
        }
    }

    /**
     * Return the object (file or folder) name
     * @return a string of the object (file or folder) name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the object ID
     * @return an integer value of the object (file or folder) ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Return the parent of the object (file or folder)
     * @return the object of the parent's object
     */
    public FileSystemObject getParent() {
        return this.parent;
    }

    /**
     * Return the list of children of the object 
     * @return a list of the object's children
     */
    public OrderedListADT<FileSystemObject> getChildren() {
        return this.children;
    }

    /**
     * Set name of the object
     * @param name to be the object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set parent of the object
     * @param parent to the be object parent of the object
     */
    public void setParent(FileSystemObject parent) {
        this.parent = parent;
    }

    /**
     * Check if this object is a file by checking if it is an instance of ComputerFile
     * If not then it is a folder
     * @return true if this is a file, false otherwise
     */
    public boolean isFile() {
        return (this instanceof ComputerFile);
    }

    /**
     * Add a given child to this object
     * If this is a file or this object already had a file with the same name as another,
     * throw a directoryTreeException
     * @param node the specified child to be added
     * @throws DirectoryTreeException if this is a file or if this is a duplicate name
     */
    public void addChild(FileSystemObject node) throws DirectoryTreeException {
        if (isFile()) {
            throw new DirectoryTreeException("We cannot store a file/folder within a file.");
        }

        // we already check if this is a file or a folder above, so we know this is a folder
        // if the folder doesnt have any child, initialize a list of child so the new child can be added
        if (children == null) {
            children = new ArrayOrderedList<>();
        }

        // check if there is a file in the folder with the same name as the specified node
        if (checkDuplicateName(node.getName())) {
            throw new DirectoryTreeException("We cannot have two files/folder with the same name stored in the same folder.");
        }
        // if this is a folder, add the given node as a child of this
        node.setParent(this);
        children.add(node);
    }

    /**
     * Check if there is another file with the same name
     * @param name of this file
     * @return true if there is a file with the same name, false otherwise
     */
    private boolean checkDuplicateName(String name) {
        // for each children (file) in the folder, check if their are two of the same name
        for (FileSystemObject child : children) {
            if (child.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the name of this object
     * @return a string with the file or folder name
     */
    public String toString() {
        return this.name;
    }

    /**
     * Return the file or folder size.
     * If its a folder, use folderSize method to check all the files size
     * @return the size of the file or folder
     */
    public int size() {
        if (isFile()) {
            return ((ComputerFile) this).size(); // if its a file we need to check the size in ComputeFile
        } else {
            return folderSize(this);
        }
    }

    /**
     * Calculates the specified folder size
     * @param folder is the specified folder to calculate the size
     * @return the folder size
     */
    private int folderSize(FileSystemObject folder) {
        int size = 0;
        // For every item in the folder, calculate the size (file or folder)
        for (FileSystemObject child : folder.getChildren()) {
            size += child.size();
        }
        return size;
    }

    /**
     * Compares a file and a folder
     * Return an appropriate int value to indicate which FileSystemObject is larger
     * @param other is the other object being compare to this
     * @return an appropriate int value to indicate which FileSystemObject is larger 
     */
    public int compareTo(FileSystemObject other) {
        // if this is a file and the other is a folder, then this is smaller therefore
        // should come before file
        if (this.isFile() && !other.isFile()) {
            return 1;
        }
        // if this is a folder and the other is a file, then this is larger
        else if (!this.isFile() && other.isFile()) {
            return -1;
        }

        // if they are both file or folder, use build in compare to check alphabetical
        // not case sentitive ordering
        return (this.name.compareToIgnoreCase(other.name));
    }

}