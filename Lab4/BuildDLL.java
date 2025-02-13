
public class BuildDLL {
	
	private DoubleLinkedNode<Character> front, rear;
	private static char[] letters = new char[] {'K', 'T', 'E', 'N', 'P', 'A', 'L'};

	public BuildDLL () {
		build();
	}
	
	

	public void remove (Character elem) {
		DoubleLinkedNode<Character> curr = front;
    
		// Traverse the list to find the node to remove
		while (curr != null && !curr.getElement().equals(elem)) {
			curr = curr.getNext();
		}
		
		// If the element was not found, return
		if (curr == null) {
			return;
		}
		
		// Case 1: Removing the front node
		if (curr == front) {
			front = front.getNext();
			if (front != null) {
				front.setPrevious(null);
			} else { // If front is null, the list is empty
				rear = null;
			}
		} 
		// Case 2: Removing the rear node
		else if (curr == rear) {
			rear = rear.getPrevious();
			if (rear != null) {
				rear.setNext(null);
			} else { // If rear is null, the list is empty
				front = null;
			}
		} 
		// Case 3: Removing an internal node
		else {
			curr.getPrevious().setNext(curr.getNext());
			curr.getNext().setPrevious(curr.getPrevious());
		}


	}
	
	
	private void build () {
		DoubleLinkedNode<Character> pnode, node;
		
		node = new DoubleLinkedNode<Character>(letters[0]);
		pnode = front = node;
		for (int i = 1; i < 7; i++) {
			node = new DoubleLinkedNode<Character>(letters[i]);
			pnode.setNext(node);
			node.setPrevious(pnode);
			pnode = node;
		}
		rear = node;
	}
	
	public DoubleLinkedNode<Character> getFront () {
		return front;
	}
	
	public DoubleLinkedNode<Character> getRear () {
		return rear;
	}
	
	public void printF (DoubleLinkedNode<Character> node) {
		System.out.print("Forward:  ");
		DoubleLinkedNode<Character> curr = front;
		while (curr != null) {
			System.out.print(curr.getElement() + " ");
			curr = curr.getNext();
		}
		System.out.print("\n");
	}
	
	public void printR (DoubleLinkedNode<Character> node) {
		System.out.print("Reverse:  ");
		DoubleLinkedNode<Character> curr = rear;
		while (curr != null) {
			System.out.print(curr.getElement() + " ");
			curr = curr.getPrevious();
		}
		System.out.print("\n");
	}
	
	
	
	public static void main (String[] args) {
		BuildDLL dll = new BuildDLL();

		System.out.println("Original List:");
		dll.printF(dll.getFront());
		dll.printR(dll.getRear());
		System.out.println("***");
		
		System.out.println("Removing an internal node:");
		dll.remove('N');
		dll.printF(dll.getFront());
		dll.printR(dll.getRear());
		System.out.println("***");
		
		System.out.println("Removing the front node:");
		dll.remove('K');
		dll.printF(dll.getFront());
		dll.printR(dll.getRear());
		System.out.println("***");
		
		System.out.println("Removing the rear node:");
		dll.remove('L');
		dll.printF(dll.getFront());
		dll.printR(dll.getRear());
		System.out.println("***");
		
		System.out.println("Trying to remove a non-existent node:");
		dll.remove('X');
		dll.printF(dll.getFront());
		dll.printR(dll.getRear());
		System.out.println("***");
	}

}
