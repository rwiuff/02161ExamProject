package taskfusion;

public class Employee {

    private String firstName;
    private String lastName;
    private String initials;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = firstName.substring(0, 2) + lastName.substring(0, 2);

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInitials() {
        return initials;
    }

}
