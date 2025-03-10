package backend.Customer;

import backend.User.Role;
import backend.User.UserRepository;
import backend.User.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public CustomerService(UserService userService, UserRepository userRepository, CustomerRepository customerRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean validate(CustomerDTO customer) {
        boolean isValid = customer != null &&

                customer.getFirstName() != null &&
                customer.getMiddleName() != null &&
                customer.getLastName() != null &&

                !customer.getFirstName().isBlank() &&
                !customer.getMiddleName().isBlank() &&
                !customer.getLastName().isBlank()
                ;
        return userService.validate(customer) && isValid &&
                customer.getRole().equals(Role.CUSTOMER.getName());
    }

    @Override
    public boolean available(CustomerDTO customer) {
        if (customer == null) return false;
        return userRepository.findByEmail(customer.getUsername()).isEmpty();
    }

    @Override
    public boolean available(CustomerDTO customer, Long userId) {
        if (customer == null) return false;
        return userRepository.findByEmail(customer.getUsername()).isEmpty() || userRepository.findByEmail(customer.getUsername()).get().getId().equals(userId);
    }

    @Override
    public boolean register(CustomerDTO customer) {
        assert customer != null;
        Customer actualcustomer = new Customer();
        actualcustomer.setEmail(customer.getUsername());
        actualcustomer.setPassword(customer.getPassword());
        actualcustomer.setRole(Role.CUSTOMER);
        actualcustomer.setFirstName(customer.getFirstName());
        actualcustomer.setMiddleName(customer.getMiddleName());
        actualcustomer.setLastName(customer.getLastName());
        customerRepository.save(actualcustomer);
        return true;
    }

    @Override
    public Customer update(Long id, CustomerDTO customer) {
        if (id == null || id <= 0 || customer == null) {
            return null;
        }
        Customer actualcustomer = new Customer(id, customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName());
        actualcustomer.setId(id);
        actualcustomer.setEmail(customer.getUsername());
        actualcustomer.setPassword(customer.getPassword());
        actualcustomer.setFirstName(customer.getFirstName());
        actualcustomer.setMiddleName(customer.getMiddleName());
        actualcustomer.setLastName(customer.getLastName());
        customerRepository.save(actualcustomer);
        return actualcustomer;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Customer delete(Long id) {
        return customerRepository.findCustomerById(id).map(customer -> {
            customer.setIsDeleted(true);
            customerRepository.save(customer);
            return customer;
        }).orElse(null);
    }

    @Override
    public Optional<Customer> getCustomerByUsername(String username) {
        return customerRepository.findCustomerByEmail(username);
    }
}
