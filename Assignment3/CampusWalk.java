/*
CS 1027B – Assignment 3
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: March 8, 2025
*/

import java.util.Stack;


public class CampusWalk {
    private Map map;

    /**
     * Constructor that initializes the map and displays the GUI based on user preference
     * 
     * @param filename The name of the file containing the map data
     * @param showMap  Boolean indicating whether to display the GUI
     */
    public CampusWalk (String filename, boolean showMap) {
        try {
            map = new Map(filename);
            if (showMap) {
                map.showGUI();
            } else {
                map.hideGUI();
            }
        } catch (Exception e) {
            System.out.println("Error occurred.");
        }
        
    }

    /**
     * Counts the number of neighboring cells that contain geese.
     * 
     * @param cell The hexagonal cell to check.
     * @return The count of neighboring cells that contain geese.
     */

    public int neighbourGooseCount(Hexagon cell) {
        int gooseCount = 0;

        for (int i = 0; i < 6; i++) {
            try {
                Hexagon neighbour = cell.getNeighbour(i);

                if (neighbour != null && neighbour.isGooseCell()) {
                    gooseCount++;
                }

            } catch (Exception e) {
                System.out.println("Invalid neighbour index");
            }
        }

        return gooseCount;
    }

    /**
     * Finds the best neighboring cell to move to based on certain criteria
     * 
     * @param cell The current hexagonal cell
     * @return The best neighboring cell to move to, or null if no suitable cell is found
     */
    public Hexagon findBest(Hexagon cell) {
        Hexagon bestCell = null;
        
        // First check if any of the adjacent cells is the end cell
        // If it is the end cell go to it
        for (int i = 0; i < 6; i++) {
            Hexagon neighbour = cell.getNeighbour(i);

            if (neighbour == null) {
                continue;
            }

            if (neighbour.isEnd()) {
                return neighbour;
            }

        }
        
        for (int i = 0; i < 6; i++) {
            try {
                Hexagon neighbour = cell.getNeighbour(i);

                /* 
                * skip cell if the adjacent cell is a goose cell, or the cell has a goose count of 3 or more
                * or if the cell is already marked
                */ 
                
                if (neighbour == null || neighbour.isMarked() || neighbour.isGooseCell() || 
                    (neighbourGooseCount(neighbour) >= 3 && !neighbour.isEnd())) {
                    continue;
                }

                // 1. Check if curr is adj to a book cell
                if (neighbour.isBookCell()) {
                    if (bestCell == null || !bestCell.isBookCell() || bestCell.getID() > neighbour.getID()) {
                        bestCell = neighbour;
                    }
                    break;
                }
    
                // 2. Check if curr is adj to grass cell
                if (neighbour.isGrassCell()) {
                    if (bestCell == null || !bestCell.isGrassCell() || 
                        neighbourGooseCount(bestCell) > neighbourGooseCount(neighbour)) {
                        bestCell = neighbour;
                    }
                    continue;
                }
    
                // 3. Check if curr is adj to snow cell
                if (neighbour.isSnowCell()) {
                    if (bestCell == null || (bestCell.isSnowCell() && bestCell.getID() > neighbour.getID())) {
                        bestCell = neighbour;
                    }
                }
    
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    
        return bestCell;
    }
    
    /**
     * Finds a path from the start cell to the end cell using a stack-based depth-first search.
     * 
     * @return A string representation of the path taken (sequence of cell IDs), or "No path found" if no path exists.
     */

    public String findPath() {
        Stack<Hexagon> stack = new Stack<>();
        Hexagon start = map.getStart();
        stack.push(start);

        boolean running = true;

        start.markInStack();

        String result = "";

        while (!stack.isEmpty() && running) {
            Hexagon curr = stack.peek();
            result += curr.getID() + " ";
            if (curr.isEnd()) {
                running = false;
                break;
            }

            Hexagon next = findBest(curr);

            if (next == null) {
                stack.pop();
                curr.markOutStack();
            } else {
                stack.push(next);
                next.markInStack();
            }
        }
        if (!running) {
            return result;
        } else {
            return "No path found";
        }
    }

    /**
     * Exits the map, closing any open resources or GUI elements.
     */
    public void exit () {
        map.exit();
    }

    public static void main(String[] args) {
        Hexagon.TIME_DELAY = 500; // Change speed of animation.
        String file = "map5.txt"; // Change when trying other maps.
        CampusWalk walk = new CampusWalk(file, true);
        String result = walk.findPath();
        System.out.println(result);
        }
        
}
