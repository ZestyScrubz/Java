public class ReversibleArray<T> {
    private T[] array;
    private int count;

    public ReversibleArray(T[] array) {
        this.array = array;
        count = array.length;
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < count - 1; i++) {
            string += String.format("elem%d", i);
            string += ", ";
        }

        string += String.format("elem%d", count - 1);

        return string;
    }

    public void reverse() {

        T temp;

        for (int i = 0; i < count / 2; i++) {
            temp = array[i];
            array[i] = array[count - i - 1];  
            array[count - i - 1] = temp;
        }
    }



}
