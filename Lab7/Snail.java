public class Snail {
    
    public static final char icon = '@';
    private int position;
    private QueueADT<Integer> movePattern;
    
    public Snail (int[] pattern) {
        position = 0;
        movePattern = new DLQueue<>(); // Assuming DLQueue is the implementation for QueueADT
        for (int step : pattern) {
            movePattern.enqueue(step);
        }
    }
    
    public void move() {
        int step = movePattern.dequeue();
        movePattern.enqueue(step);
        position += step;
        
        // Accessing race length directly from SnailRace
        int raceLength = SnailRace.raceLength;
        if (position > raceLength) {
            position = raceLength;
        }
    }
    
    public int getPosition() {
        return position;
    }
    
    public void display() {
        int raceLength = SnailRace.raceLength;
        int dashesBefore = position;
        int dashesAfter = raceLength - position;
        
        for (int i = 0; i < dashesBefore; i++) {
            System.out.print("-");
        }
        System.out.print(icon);
        for (int i = 0; i < dashesAfter; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
