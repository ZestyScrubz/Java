/*
CS 1027B – Assignment 4
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: March 28, 2025
*/

public class DirectoryTree {
    private FileSystemObject root;

    public DirectoryTree(FileSystemObject rt) {
        root = rt;
    }

    public FileSystemObject getRoot() {
        return root;
    }

    /**
     * Determine and return the level of the given file/folder
     * fso, in the tree (the root isat level 0)
     * @param fso is the specified object to check the level
     * @return the level of the file or folder
     */
    public int level(FileSystemObject fso) {

        // if fso is the root, then the level is 0
        if (fso.getParent() == null) return 0;
        else return 1 + level(fso.getParent());

    }

    /**
     * Determine and return the lowest common ancestor of nodes a and b in the tree.
     * @param a is the first object to be compared
     * @param b is the second object to be compared
     * @return the lowest common ancestor
     */
    public FileSystemObject lca(FileSystemObject a, FileSystemObject b) {
        return determineLca(a, b);
    }

    /**
     * Helper method that recusively check for the lowest common ancestor by checking
     * if the parents of the two nodes are the same
     * @param a is the first object
     * @param b is the second object
     * @return the lowest common ancestor
     */
    private FileSystemObject determineLca(FileSystemObject a, FileSystemObject b) {
        int levelA = level(a); // get the level of node a
        int levelB = level(b); // get the level of node b
    
        // move b up until it matches the level of a
        while (levelA < levelB) {
            b = b.getParent();
            levelB--;
        }

        while (levelB < levelA) {
            a = a.getParent();
            levelA--;
        }

        // find the lowest common ancestor
        // base case:
        if (a == b) return a;
        // look at parent node of a&x or b&x or a&b and determine if the two nodes are the same
        return determineLca(a.getParent(), b.getParent());
    }

    /**
     * The file path from node a to node b in the tree
     * @param a is the first object
     * @param b is the second object
     * @return a string of the file path from node a to b with / seperating each path segment
     */
    public String buildPath(FileSystemObject a, FileSystemObject b) {

        // if a and b are in the same folder
        if (a.getParent() == b.getParent()) {
            return b.getName();
        }

        FileSystemObject ancestor = lca(a, b);

        String pathUp = "";
        while (a != ancestor) {
            pathUp += "../";
            a = a.getParent();
        }

        // build the path downward from the ancestor to b
        String pathDown = "";

        // skip the first file to remove / at the end
        pathDown = b.getName()  + pathDown;
        b = b.getParent();

        while (b != ancestor) {

            pathDown = b.getName() + "/" + pathDown;  // Add a slash only for directories
            b = b.getParent();
        }

        // combine the upward and downward paths
        return pathUp + pathDown;
    
    }

    /**
     * Return a string representing the entire directory tree
     * @return a string representing the entire directory tree
     */
    public String toString() {
        return buildTree(root, 0);
    }

    /**
     * Helper method to help print the tree with all indentation
     * @param node is the specified object to indent
     * @param depth is the depth of the node
     * @return a string of the entire directory tree
     */
    private String buildTree(FileSystemObject node, int depth) {
        String result = "";

        // generate indentation based on depth
        String indentation = "";
        for (int i = 1; i < depth; i++) {
            indentation += "  ";
        }

        // if the node is the root, don't add the dash before the name
        if (node.getParent() == null) {
            result = node.getName() + "\n";  // for the root node, just print the name without '-'
        } else {
            result = indentation + " - " + node.getName() + "\n";  // for other nodes, add the dash
        }

        // handle null children to prevent crashes
        if (node.getChildren() != null) {
            for (FileSystemObject child : node.getChildren()) {
                result += buildTree(child, depth + 1);
            }
        }

        return result;
    }

    /**
     * Moves a file or folder from its current location to a new destination.
     * @param f is file or folder to be moved.
     * @param dest is destination folder where the file or folder should be moved.
     * @throws DirectoryTreeException if attempting to move the root or if the destination is a file.
     */
    public void cutPaste(FileSystemObject f, FileSystemObject dest) throws DirectoryTreeException{
        if (dest.isFile()) throw new DirectoryTreeException("We cannot store file/folder inside a file.");
        if (f == root) throw new DirectoryTreeException("We cannot remove/cut/move the root of the whole tree.");
        f.getParent().getChildren().remove(f);
        dest.addChild(f);
    }

    /**
     * Creates a copy of a file or folder and places it in the specified destination.
     * @param f is the file or folder to be copied.
     * @param dest is the destination folder where the copy should be placed.
     * @throws DirectoryTreeException if the destination is a file.
     */
    public void copyPaste(FileSystemObject f, FileSystemObject dest) throws DirectoryTreeException {
        if (dest.isFile()) throw new DirectoryTreeException("Cannot copy into a file.");
        
        FileSystemObject clone = cloneObject(f, 100);
        dest.addChild(clone);
    }

    /**
     * Helper method that recursively clones a file or folder, assigning a new unique ID.
     * @param original is the file or folder to be cloned.
     * @param newID is the unique ID offset to be assigned to the clone.
     * @return a new FileSystemObject that is a deep copy of the original.
     */
    private FileSystemObject cloneObject(FileSystemObject original, int newID) {

        FileSystemObject clone;

        // if the object is a file and not a folder, copy the file and add it to another FilesystemObject
        if (original.isFile()) {
            int fileSize = ((ComputerFile) original).size();
            clone = new ComputerFile(original.getName(), original.getID() + newID, fileSize);
        } else {
            clone = new FileSystemObject(original.getName(), original.getID() + newID);
            for (FileSystemObject child : original.getChildren()) {
                FileSystemObject childClone = cloneObject(child, newID);
                childClone.setParent(clone);
                clone.addChild(childClone);
            }
        }
        return clone;
    }
}
