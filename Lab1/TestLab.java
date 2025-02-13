/*
 * Isaac Tran
 * Computer Science 1027B
 * 2025-01-13
 */

public class TestLab {
    public static void main(String[] args) {
        Player p1, p2;

        p1 = new Player("Isaac", "Offence" , 97);
        System.out.println(p1.getName());
        System.out.println(p1.getPosition());
        System.out.println(p1.getJerseyNum());

        p1.setName("Cale Makar");
        p1.setPosition("Defence");
        p1.setJerseyNum(8);

        System.out.println(p1.getName());
        System.out.println(p1.getPosition());
        System.out.println(p1.getJerseyNum());

        System.out.println(p1);

        p2 = new Player("Cale Makar", "Defence", 8);
        
        if (p1.equals(p2)) {
            System.out.println("Same player");
        } else {
            System.out.println("Different player");
        }
           
    }
}
