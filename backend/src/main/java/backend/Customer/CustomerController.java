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


    /**
     * This our read method
     * */
    @GetMapping("/")
    public ResponseEntity<Customer> getCustomer() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerService.getCustomerByUsername(username).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * This is our update
     * */
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CustomerDTO customerDto) {
        boolean isValidated = customerService.validate(customerDto);
        boolean valid = isValidated && customerService.available(customerDto, id);

        Customer customer = customerService.update(id, customerDto);
        if (valid && customer!=null) {
            return ResponseEntity.ok(customer);
        }

        return ResponseEntity.badRequest().body(isValidated ? "Username is already taken!" : "Invalid details supplied!");
    }

    /**
     * This is our delete
     * */
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

}


