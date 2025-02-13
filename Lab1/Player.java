/*
 * Isaac Tran
 * Computer Science 1027B
 * 2025-01-13
 */

public class Player {
    
    // Instantiate class variables
    private String name;
    private String position;
    private int jerseyNum;

    // Constructor method
    public Player(String name, String position, int jerseyNum) {
        this.name = name;
        this.position = position;
        this.jerseyNum = jerseyNum;
    }

    // Getter methods
    public String getName() {
        return this.name;
    }
    public String getPosition() {
        return this.position;
    }
    public int getJerseyNum() {
        return this.jerseyNum;
    }

    // Setter methods
    public void setName(String newName) {
        name = newName;
    }
    public void setPosition(String newPosition) {
        position = newPosition;
    }
    public void setJerseyNum(int newJerseyNum) {
        jerseyNum = newJerseyNum;
    }

    public String toString() {
        return this.name + ": #" + this.jerseyNum;
    }

    public boolean equals(Player other) {
        if (this.name.equals(other.name) && this.jerseyNum == (other.jerseyNum)) {
            return true;
        }
        else {
            return false;
        }
    }
}