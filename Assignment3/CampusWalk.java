public class CampusWalk {
    private Map map;

    public CampusWalk (String filename, boolean showMap) {
        try {
            map = filename;
            if (showMap) {
                map.showGUI();
            } else {
                map.hideGUI();
            }
        } catch (Exception e) {
            System.out.println("Error occurred.");
        }
        
    }

    public int neighbourGooseCount (Hexagon cell) {

    }

    public Hexagon findBest (Hexagon cell) {

    }

    public String findPath () {

    }

    public void exit () {
        map.exit();
    }
}
