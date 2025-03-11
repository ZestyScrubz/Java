/**
 *  @author Lewis and Chase
 *
 *  Represents an array implementation of a stack.
 */

public class ArrayStack<T> implements StackADT<T> {
	/**
	 * constant to represent the default capacity of the array
	 */
	private final int DEFAULT_CAPACITY = 100;

	/**
	 * int that represents both the number of elements and the next
	 * available position in the array
	 */
	private int top;  

	/**
	 * array of generic elements to represent the stack
	 */
	private T[] stack;

	/**
	 * Creates an empty stack using the default capacity.
	 */
	public ArrayStack() {
		top = 1; // Do not change this!
		stack = (T[])(new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Creates an empty stack using the specified capacity.
	 * @param initialCapacity represents the specified capacity 
	 */
	public ArrayStack (int initialCapacity) {
		top = 1; // Do not change this!
		stack = (T[])(new Object[initialCapacity]);
	}

	/**
	 * Adds the specified element to the top of this stack, expanding
	 * the capacity of the stack array if necessary.
	 * @param element generic element to be pushed onto stack
	 */
	public void push(T element) {
		if (top == stack.length) {
			expandCapacity();
		}
		stack[top++] = element;
	}
	/**
	 * Removes the element at the top of this stack and returns a
	 * reference to it. Throws an EmptyCollectionException if the stack
	 * is empty.
	 * @return T element removed from top of stack
	 * @throws EmptyCollectionException if a pop is attempted on empty stack
	 */
	public T pop() throws EmptyCollectionException {
		// ADD CODE HERE.
		if (isEmpty()) {
			throw new EmptyCollectionException("Stack is empty");
		}

		T temp = stack[--top];
		stack[top] = null;
		return temp;
	}

	/**
	 * Returns a reference to the element at the top of this stack.
	 * The element is not removed from the stack.  Throws an
	 * EmptyCollectionException if the stack is empty.  
	 * @return T element on top of stack
	 * @throws EmptyCollectionException if a peek is attempted on empty stack
	 */
	public T peek() throws EmptyCollectionException {
		// ADD CODE HERE.
		if (isEmpty()) {
			throw new EmptyCollectionException("Stack is empty");
		}

		return stack[top - 1];
	}

	/**
	 * Returns true if this stack is empty and false otherwise. 
	 * @return boolean true if this stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		// ADD CODE HERE.
		for (int i = 0; i < size(); i++) {
			if (stack[i] != null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the number of elements in this stack.
	 * @return int the number of elements in this stack
	 */
	public int size() {
		// ADD CODE HERE.
		return top;
	}

	/**
	 * Returns a string representation of this stack. 
	 * @return String representation of this stack
	 */
	public String toString() {
		if (isEmpty()) {
			return "The stack is empty.";
		}
	
		String result = "Stack: ";
		for (int i = top - 1; i > 1; i--) {
			result += stack[i] + ", ";
		}
		result += stack[1] + ".";
	
		return result;
	}

	/**
	 * Creates a new array to store the contents of this stack with
	 * twice the capacity of the old one.
	 */
	private void expandCapacity() {
		T[] larger = (T[])(new Object[stack.length*2]);

		for (int index=1; index < stack.length; index++)
			larger[index] = stack[index];

		stack = larger;
	}
	
	public int getTop () {
		return top; // Do not change this!
	}

}

