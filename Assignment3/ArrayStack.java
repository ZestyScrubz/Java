public class ArrayStack<T> implements StackADT<T> {

    private T[] array;
    private int top;

    public ArrayStack() {
        array = (T[])(new Object[10]);
        top = array.length - 1;
    }

    public ArrayStack(int initCapacity) {
        array = (T[])(new Object[initCapacity]);
        top = array.length - 1; 

    }

    public void push(T element) {
        if (top == -1) expandCapacity();

        array[top--] = element;
    }

    public T pop() throws CollectionException {
        if (isEmpty()) throw new CollectionException("Stack is empty");

        T temp = array[++top];
        array[top] = null;
        return temp;
    }

    public T peek() throws CollectionException {
        if (isEmpty()) throw new CollectionException("Stack is empty");

        return array[top + 1];
    }

    public boolean isEmpty() {
        return (top == array.length - 1);
    }
    
    public int size() {
        return array.length - 1 - top;
    }

    public int getCapacity() {
        return array.length;
    }

    public int getTop() {
        return top;
    }

    public String toString() {
        if (isEmpty()) return "Empty stack.";

        String result = "";

        for (int i = top + 1; i < array.length - 1; i++) {
            result += array[i] + ", ";
        }

        result += array[array.length - 1] + ", ";

        return result;
    }

    private void expandCapacity() {
        if (array.length <= 15) {
            T[] larger = (T[])(new Object[array.length*2]);
            int startIndex = larger.length - array.length;
            for (int index = 0; index < array.length; index++) {
                larger[startIndex + index] = array[index];
            }

            array = larger;
            top = startIndex - 1;
        } else {
            T[] larger = (T[])(new Object[array.length + 10]);

            int startIndex = larger.length - array.length;
            for (int index = 0; index < array.length; index++) {
                larger[startIndex + index] = array[index];
            }

            array = larger;
            top = startIndex - 1;
        }
    }

    
}