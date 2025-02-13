
public class SimpleExceptions {

	public static void main(String[] args) {
		
		String[] strArr = new String[5];

		// a)
		// System.out.println(strArr[0].length());
		
		// b)
		// strArr[5] = "I love Java";
		
		// c)
		// int x = 5 / 0;
		
		// d)
		//Object obj = (String)(new Object());
		
		// e)
		// Integer.parseInt("hi");

		
		
		m2(7);
		
		System.out.println("Finished.");
	}
	
	public static void m1 (int x) {
		String[] strArr = new String[5];
		System.out.println(strArr[x].length());
	}
	
	public static void m2 (int x) {
		try {
			m1(x);
		} catch (NullPointerException e) {
			System.out.println("Caught N.P.E.");
		} catch (Exception e) {
			System.out.println("Caught another exception");
		}
	}

}
