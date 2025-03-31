
public class TreeAlgorithms {

    public static int min(BinaryTreeNode<Integer> node) {
        // Base case: if the node is null, return a large number
        if (node == null) {
            return 9999;
        }

        // Get the value of the current node
        int value = node.getData();

        // Recursively find the min in left and right subtrees
        int leftMin = min(node.getLeft());
        int rightMin = min(node.getRight());

        // Return the smallest value among the three
        return Math.min(value, Math.min(leftMin, rightMin));
    }
	
    public static int countLeafs(BinaryTreeNode<Integer> node) {
        // Base case: if the node is null, return 0
        if (node == null) {
            return 0;
        }

        // If the node has no children, it's a leaf node
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }

        // Recursively count leaf nodes in left and right subtrees
        return countLeafs(node.getLeft()) + countLeafs(node.getRight());
    }
	



	public static void main(String[] args) {
		LinkedBinaryTree<Integer> tree = buildTree();
		System.out.println(tree);

		// Testing min
		if (min(tree.getRoot()) == 1 && min(tree.getRoot().getLeft()) == 1 && min(tree.getRoot().getRight()) == 7) {
			System.out.println("Min value test passed");
		} else {
			System.out.println("Min value test failed");
		}
		
		
		// Testing count leafs
		if (countLeafs(tree.getRoot()) == 5 && countLeafs(tree.getRoot().getLeft()) == 3 && countLeafs(tree.getRoot().getRight()) == 2) {
			System.out.println("Count leafs test passed");
		} else {
			System.out.println("Count leafs test failed");
		}

	}
	
	
	
	private static LinkedBinaryTree<Integer> buildTree () {

		LinkedBinaryTree<Integer> leftTree;
		LinkedBinaryTree<Integer> rightTree;

		// construct the left subtree of the binary tree

		LinkedBinaryTree<Integer> t1 = new LinkedBinaryTree<Integer>(1);
		LinkedBinaryTree<Integer> t2 = new LinkedBinaryTree<Integer>(3);
		LinkedBinaryTree<Integer> t3 = new LinkedBinaryTree<Integer>(2,t1,t2);
		t1 = new LinkedBinaryTree<Integer>(5);
		leftTree = new LinkedBinaryTree<Integer>(4,t3,t1);

		// construct the right subtree of the binary tree

		t1 = new LinkedBinaryTree<Integer>(9);
		t2 = new LinkedBinaryTree<Integer>(10,t1,null);
		t3 = new LinkedBinaryTree<Integer>(7);
		rightTree = new LinkedBinaryTree<Integer>(8,t3,t2);

		return new LinkedBinaryTree<Integer>(6, leftTree, rightTree);
	}

}
