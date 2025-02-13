package Inheritance;

public class TestAB {

    public static void main(String[] args){
        A obj1 = new A(10);
        B obj2 = new B(10);
        A obj3 = new B(10);
        //B obj4 = new A(10);

        int r1 = obj1.calculate();
        System.out.println(r1);
        //obj1.run(); // won't run

        int r2 = obj2.calculate();
        System.out.println(r2);
        //obj2.run(); // will run

        int r3 = obj3.calculate();
        System.out.println(r3);
        //obj3.run(); // won't run // There are two stages, the compilation and the runtime
        // when we compile this code -- Does the variable of obj3 contain the method -- no since the variable that is referencing it is of type A

        /*
         * Its good to know this stuff for the midterm
         */

    }

}
