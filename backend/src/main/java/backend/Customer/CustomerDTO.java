package backend.Customer;

import backend.Registration.UserDTO;
import backend.User.Role;

public class CustomerDTO extends UserDTO {

    private String firstName;
    private String middleName;
    private String lastName;


    public CustomerDTO(String username, String password, String firstName, String middleName, String lastName) {
        super(username, password, Role.CUSTOMER.getName());
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
