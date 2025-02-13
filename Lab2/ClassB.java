
public class ClassB {

	private int x;
	private static int y;

	public static void main(String[] args) {
		ClassB obj = new ClassB();
		obj.x = 10;

		ClassB obj2 = new ClassB();
		obj2.x = 20;
		y = 10;
		y = 30;

		System.out.println(obj.x);
		System.out.println(obj2.x);
		System.out.println(obj2.y);

	}

}
