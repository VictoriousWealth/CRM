package backend.Customer;

import backend.User.Role;
import backend.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "crm_customer")
public class Customer extends User {

    private String firstName;
    private String middleName;
    private String lastName;

    public Customer() {}

    public Customer(String username, String email, String firstName, String middleName, String lastName) {
        super(username, email, Role.VENDOR);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
