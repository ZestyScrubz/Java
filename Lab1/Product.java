/*
 * Isaac Tran
 * Computer Science 1027B
 * 2025-01-13
 */

public class Product {
	
	private String code;
	private String name;
	private double price;

	public Product (String code, String name, double cost) {
		this.code = code;
		this.name = name;
		price = cost;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName () {
		return name;
	}
	
	public double getPrice () {
		return price;
	}
	
	public void setCode (String newCode) {
		code = newCode;
	}
	
	public void setName (String newName) {
		name = newName;
	}
	
	public void setPrice (double newPrice) {
		price = newPrice;
	}
	
	public String toString () {
		return code + ": " + name + " cost: $" + price;
	}
	
	public static void main (String[] args) {
		Product prod = new Product("BRW-264825", "PlayStation 5", 599.99);

		System.out.println(prod.getCode());
		System.out.println(prod.getName());
		System.out.println(prod.getPrice());
	}
	
}
