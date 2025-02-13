
public class Person {
    private int age;
    private String lastName;
    private String firstName;
    private String email;

    public Person(int theAge, String first, String last, String theEmail) {
        age = theAge;
        firstName = first;
        lastName = last;
        email = theEmail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean equals(Person other){
        if (this.age == other.age && this.firstName == other.firstName && this.lastName == other.lastName && this.email == other.email) return true;
        else return false;
    }
}
