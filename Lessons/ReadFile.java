
public class ReadFile {

    public static void main(String[] args) {

        int count = 0;

        TextFileReader fr = new TextFileReader("Lessons\\students_marks.csv");

        if (!fr.endOfFile()) {
            fr.readString(); // Discard the first line
        }
    
        while (!fr.endOfFile()) {
            String line = fr.readString();
            String[] data = line.split(",");

            if (Integer.parseInt(data[1]) >= 90) {
                count++;
                System.out.println(data[1]);
            }
            
    
        }
        
        System.out.println(count);
        fr.close();
    }
}
