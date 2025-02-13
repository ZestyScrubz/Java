public class ClassA {
	private final int SIZE_ARRAY = 5;
	public int numItems;
	public int[] arrItems;

	public ClassA(int n, int[] arr) {
		numItems = n;
		arrItems = arr;
	}

	public ClassA() {
		numItems = 0;
		arrItems = new int[SIZE_ARRAY];
	}

	public boolean equals(ClassA other){
		if (this.numItems == other.numItems){
			for (int i = 0; i < arrItems.length; i++){
				if (this.arrItems[i] != other.arrItems[i]) return false;
			}
			return true;
		}
		else return false;
	}
}
