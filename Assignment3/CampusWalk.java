import java.util.Stack;

public class CampusWalk {
    private Map map;

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

    public Hexagon findBest(Hexagon cell) {
        Hexagon bestCell = null;

        for (int i = 0; i < 6; i++) {
            try {
                Hexagon neighbour = cell.getNeighbour(i);

                if (neighbour == null || neighbour.isMarked() || neighbour.isGooseCell() || (neighbourGooseCount(neighbour) >= 3 && !neighbour.isEnd())) {
                    continue;
                }

                if (neighbour.isEnd()) {
                    return neighbour;
                }
                
                // 2. Check if curr is adj to a book cell
                if (neighbour.isBookCell()) {
                    if (bestCell == null || !bestCell.isBookCell() || bestCell.getID() > neighbour.getID()) {
                        bestCell = neighbour;
                    }
                    break;
                }
                
                // 3. Check if curr is adj to grass cell
                if (neighbour.isGrassCell()) {
                    if (bestCell == null || !bestCell.isGrassCell() || neighbourGooseCount(bestCell) > neighbourGooseCount(neighbour)) {
                        bestCell = neighbour;
                    }
                    continue;
                }
                
                // 4. Check if curr is adj to snow cell
                if (neighbour.isSnowCell()) {
                    if (bestCell == null || bestCell.isSnowCell() && bestCell.getID() > neighbour.getID()) {
                        bestCell = neighbour;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    
        return bestCell;
    }

    public String findPath() {
        Stack<Hexagon> stack = new Stack<>();
        Hexagon start = map.getStart();
        stack.push(start);

        boolean running = true;

        start.markInStack();

        while (!stack.isEmpty() && running) {
            Hexagon curr = stack.peek();
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
            String result = "";

            for (int i = 0; i < stack.size(); i++) {
                result += stack.get(i).getID() + " ";
            }
            return result;
        } else {
            return "No path found";
        }
    }

    public void exit () {
        map.exit();
    }

    public static void main(String[] args) {
        Hexagon.TIME_DELAY = 1000; // Change speed of animation.
        String file = "map4.txt"; // Change when trying other maps.
        CampusWalk walk = new CampusWalk(file, true);
        String result = walk.findPath();
        System.out.println(result);
        }
        
}
