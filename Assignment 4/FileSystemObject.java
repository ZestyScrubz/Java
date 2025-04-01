


public class FileSystemObject implements Comparable<FileSystemObject> {
    private String name;
    private OrderedListADT<FileSystemObject> children;
    private FileSystemObject parent;
    private int id;  

    public FileSystemObject(String name, int id) {
        this.name = name;
        this.id = id;
        
        children = new ArrayOrderedList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    public FileSystemObject getParent() {
        return this.parent;
    }

    public OrderedListADT<FileSystemObject> getChildren() {
        return this.children;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(FileSystemObject parent) {
        this.parent = parent;
    }

    public boolean isFile() {
        return children == null;
    }

    public void addChild(FileSystemObject node) throws DirectoryTreeException {
        if (isFile()) {
            throw new DirectoryTreeException("We cannot store a file/folder within a file.");
        } else if (checkDuplicateName(node.getName())) {
                throw new DirectoryTreeException("We cannot have two files/folder with the same name stored in the same folder.");
        } else {
            // if this is a folder, add the given node as a child of this
            node.setParent(this);
            children.add(node);
        }
    }

    private boolean checkDuplicateName(String name) {
        // for each children (file) in the folder, check if their are two of the same name
        for (FileSystemObject child : children) {
            if (child.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.name;
    }

    public int size() {
        if (isFile()) {
            return this.size();  
        } else {
            return folderSize(this); 
        }
    }
    
    private int folderSize(FileSystemObject folder) {
        int size = 0;
        // For every item in the folder, calculate the size (file or folder)
        for (FileSystemObject child : folder.getChildren()) {
            size += child.size(); 
        }
        return size;
    }

    public int compareTo(FileSystemObject other) {
        // if this is a file and the other is a folder, then this is smaller therefore should come before file
        if (this.isFile() && !other.isFile()) {
            return -1;
        }
        // if this is a folder and the other is a file, then this is larger
        else if (!this.isFile() && other.isFile()) {
            return 1;
        }

        // if they are both file or folder, use build in compare to check alphabetical
        // not case sentitive ordering
        return this.name.compareToIgnoreCase(other.name);
    }

}
