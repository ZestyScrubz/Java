
public class ModifyLists {

	public static void main(String[] args) {
		
		int test = 2;
		
		//
		// LL #1:   4
		//
		if (test == 1) {
			LinearNode<Integer> head1 = createLL(new int[] {4});
			printList(head1);
			modify(head1);
			printList(head1);
		}
		
		
		//
		// LL #2:   4 -> 7
		//
		if (test == 2) {
			LinearNode<Integer> head2 = createLL(new int[] {4, 7});
			printList(head2);
			modify(head2);
			printList(head2);
		}
		
		//
		// LL #3:   4 -> 7 -> 5
		//
		if (test == 3) {
			LinearNode<Integer> head3 = createLL(new int[] {4, 7, 5});
			printList(head3);
			modify(head3);
			System.out.println("A");
			printList(head3);
		}
	}
	
	
	public static LinearNode<Integer> modify (LinearNode<Integer> front) {
		LinearNode<Integer> p = front;
		LinearNode<Integer> q = p;
		
		while (p != null) {
			if (p != q) {
				p.setNext(q.getNext());
				p = p.getNext();
			} else {
				q = q.getNext();
			}
		}
		
		return front;
	}
	
	
	
	
	
	
	
	public static LinearNode<Integer> createLL (int[] array) {
		LinearNode<Integer> front = null;
		LinearNode<Integer> intNode;

		for (int i = array.length-1; i >= 0; i--) {
			intNode = new LinearNode<Integer>(array[i]);
			intNode.setNext(front);
			front = intNode;
		}
		
		return front;
	}
	
	private static void printList(LinearNode<Integer> front) {
		LinearNode<Integer> current = front;
		while (current != null) {
			System.out.print(current.getElement() + " ");
			current = current.getNext();
		}
		System.out.println();
	}

}
