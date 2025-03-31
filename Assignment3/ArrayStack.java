/*
CS 1027B – Assignment 3
Name: Isaac Tran
Student Number: 251446564
Email: itran9
Created: March 8, 2025
*/

public class ArrayStack<T> implements StackADT<T> {

    private T[] array;
    private int top;

    // Create empty stack of size 10
    public ArrayStack() {
        array = (T[])(new Object[10]);
        top = array.length - 1;
    }

    /**
     * Create empty stack with given size
     * 
     * @param initCapacity is the specified capacity
     */
    public ArrayStack(int initCapacity) {
        array = (T[])(new Object[initCapacity]);
        top = array.length - 1; 

    }

    /**
     * Add specified element to the rightmost avaialable cell (the top) and update the top value
     * Expand the stack is neccesary
     * 
     * @param element is a generic element to be pushed onto the stack
     */
    public void push(T element) {
        if (top == -1) expandCapacity();

        array[top--] = element;
    }

    /**
     * Removes the element at the top of the stack and return it
     * Throws a collectionException if necessary
     * 
     * @return T temp removed from top of stack
     * @throws CollectionException if a pop is attempted on an empty stack
     */
    public T pop() throws CollectionException {
        if (isEmpty()) throw new CollectionException("Stack is empty");

        T temp = array[++top];
        array[top] = null;
        return temp;
    }

    /**
     * Gets the element at the top of the stack without removing it
     * 
     * @return the element at the top of the stack
     * @throws CollectionException if the stack is empty
     */
    public T peek() throws CollectionException {
        if (isEmpty()) throw new CollectionException("Stack is empty");

        return array[top + 1];
    }

    /**
     * Checks whether the stack is empty
     * 
     * @return true if the stack is empty, otherwise false
     */
    public boolean isEmpty() {
        return (top == array.length - 1);
    }
    
    /**
     * Checks the number of elements in the stack
     * 
     * @return the number of elements in the stack
     */
    public int size() {
        return array.length - 1 - top;
    }

    /**
     * Gets the length/capacity of the array
     * @return the length (capacity) of array
     */
    public int getCapacity() {
        return array.length;
    }

    /**
     * Gets the index of the top
     * 
     * @return the index of the top
     */
    public int getTop() {
        return top;
    }

    /**
     * Create a string containing all the items in the stack starting from the top
     * to the bottom. If the stack is empty return empty stack
     * 
     * @return a string representing the stack
     */
    public String toString() {
        if (isEmpty()) return "Empty stack.";

        String result = "";

        for (int i = top + 1; i < array.length - 1; i++) {
            result += array[i] + ", ";
        }

        result += array[array.length - 1] + ", ";

        return result;
    }

    /**
     * Double the array capacity if the current capacity is 15 or less
     * otherwise, add 10 extra space to the array
     * 
     * @return new array with the expanded capacity
     */
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