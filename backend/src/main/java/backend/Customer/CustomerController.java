package backend.Customer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerService.getCustomerByUsername(username).map(customer -> ResponseEntity.ok("Customer is <" + customer + ">."))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        boolean isValidated = customerService.validate(customer);
        boolean valid = isValidated && customerService.available(customer);

        if (valid && customerService.update(id, customer)) {
            return ResponseEntity.ok("Customer <"+ customer.getUsername() +"> updated successfully");
        }

        return ResponseEntity.badRequest().body(isValidated ? "Username or business name is already taken!" : "Invalid details supplied!");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

}


