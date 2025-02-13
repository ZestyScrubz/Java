package Inheritance;

public class B extends A {
    
    public B(int x) {
        super(x);
    }

    public int calculate() {
        return super.calculate() - 10; // overriding the method from class A
    }

    public void run() {
        System.out.println("Car is running");
    }
}
