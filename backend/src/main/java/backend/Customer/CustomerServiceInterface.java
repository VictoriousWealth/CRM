package backend.Customer;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CustomerServiceInterface {
    /**
     * @param customer the customer to check
     * this method returns true if the customer's details provided are valid
     * */
    boolean validate(CustomerDTO customer);
    /**
     * @param customer the customer to check
     * this method returns true if the customer's customer's username and business name is unique and not already in the database
     * */
    boolean available(CustomerDTO customer);

    /**
     * @param customer the customer that needs to be registered
     *
     * */
    boolean register(CustomerDTO customer);

    /**
     * @param id the id of the customer that needs to be updated
     * @param customer the new customer details
     *
     * */
    boolean update(Long id, CustomerDTO customer);

    /**
     * @param id the id of the customer to return
     */
    Optional<Customer> getCustomerById(Long id);

    /**
     * @param id the id of the customer to delete
     * */
    boolean delete(Long id);

    /**
     * @param username the username of the customer to return
     * */
    Optional<Customer> getCustomerByUsername(String username);
}
