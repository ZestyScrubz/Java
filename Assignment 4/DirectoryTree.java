

public class DirectoryTree {
    private FileSystemObject root;

    public DirectoryTree(FileSystemObject rt) {
        root = rt;
    }

    public FileSystemObject getRoot() {
        return root;
    }

    public int level(FileSystemObject fso) {

        // if fso is the root, then the level is 0
        if (fso.getParent() == null) return 0;
        else return 1 + level(fso.getParent());

    }

    public FileSystemObject lca(FileSystemObject a, FileSystemObject b) {
        return determineLca(a, b);
    }

    private FileSystemObject determineLca(FileSystemObject a, FileSystemObject b) {
        int levelA = level(a); // Get the level of node a
        int levelB = level(b); // Get the level of node b
    
        // Move b up until it matches the level of a
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

    public String toString() {
        return buildTree(root, 0);
    }

    private String buildTree(FileSystemObject node, int depth) {
        String result = "";

        // generate indentation based on depth
        String indentation = "";
        for (int i = 0; i < depth; i++) {
            indentation += "  ";
        }

        // If the node is the root, don't add the dash before the name
        if (node.getParent() == null) {
            result = node.getName() + "\n";  // For the root node, just print the name without '-'
        } else {
            result = indentation + "- " + node.getName() + "\n";  // For other nodes, add the dash
        }

        // add lines for child nodes
        for (FileSystemObject child : node.getChildren()) {
            result += buildTree(child, depth + 1);
        }

        return result;
    }

    public void cutPaste(FileSystemObject f, FileSystemObject dest) throws DirectoryTreeException{
        if (dest.isFile()) throw new DirectoryTreeException("We cannot store file/folder inside a file.");
        if (f == root) throw new DirectoryTreeException("We cannot remove/cut/move the root of the whole tree.");
        f.getParent().getChildren().remove(f);
        dest.addChild(f);
    }

    // Copy a file/folder
    public void copyPaste(FileSystemObject f, FileSystemObject dest) throws DirectoryTreeException {
        if (dest.isFile()) throw new DirectoryTreeException("Cannot copy into a file.");
        FileSystemObject clone = cloneObject(f, 100);
        dest.addChild(clone);
    }

    private FileSystemObject cloneObject(FileSystemObject original, int newID) {
        FileSystemObject clone = new FileSystemObject(original.getName(), original.getID() + newID);
        if (!original.isFile()) {
            for (FileSystemObject child : original.getChildren()) {
                clone.addChild(cloneObject(child, newID));
            }
        }
        return clone;
    }
}
