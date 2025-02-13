
public class DebuggingExercise1 {
	
	private static final int NUM_ITEMS = 10;

	public static void m1 (int[] arr, int k) {
		// Populate array with random numbers.
		int r = (int)(Math.random() * 25);
		arr[k++] = r;
	}
	
	public static boolean continueLooping (int k) {
		// Determine if the loop should continue based on the value of k.
		if (k < NUM_ITEMS) return true;
		return false;
		
	}
	
	public static int findSmallest (int[] arr) {
		// Search for smallest value and return its index.
		int smallestValue = 50;
		int smallestIndex = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < smallestValue) {
				smallestValue = arr[i];
				smallestIndex = i;
			}
		}
		
		return smallestIndex;
	}
	
	public static void main (String[] args) {
		
		// NUM_ITEMS = 20;
		boolean done = false;
		int[] arr = new int[NUM_ITEMS];
		int k = 0;
		
		// Populate array with random numbers.
		while (continueLooping(k)) {
			m1(arr,k);
			k++;
		}
		
		int smallestIndex = findSmallest(arr);
		int smallestValue = arr[smallestIndex];
		
		// Print out smallest value and its index.
		System.out.println("Smallest value is: " + smallestValue);
		System.out.println("Found at index: " + smallestIndex);
		System.out.println();
		
		// Print out array values.
		for (int i = 0; i < NUM_ITEMS; i++) {
			System.out.print(arr[i] + "  ");
		}
		
	}

}
