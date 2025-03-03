package backend.Registration;

import backend.User.Role;
import backend.User.User;
import backend.User.UserRepository;
import backend.User.UserService;
import backend.Vendor.VendorDTO;
import backend.Vendor.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RegisterController {

    private final UserService userService;
    private final VendorService vendorService;

    public RegisterController(UserService userService, VendorService vendorService) {
        this.userService = userService;
        this.vendorService = vendorService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
        boolean isValidated = userService.validate(user);
        boolean valid = isValidated && userService.available(user);

        if (valid && userService.register(user)) {
            return ResponseEntity.ok("User <"+ user.getUsername() +"> registered successfully");
        }

        return ResponseEntity.badRequest().body(isValidated ? "Username is already taken!" : "Invalid username or password!");
    }

    @PostMapping("/vendor/register")
    public ResponseEntity<String> registerVendor(@RequestBody VendorDTO vendor) {
        boolean isValidated = vendorService.validate(vendor);
        boolean valid = isValidated && vendorService.available(vendor);

        if (valid && vendorService.register(vendor)) {
            return ResponseEntity.ok("Vendor <"+ vendor.getUsername() +"> registered successfully");
        }

        return ResponseEntity.badRequest().body(isValidated ? "Username or business name is already taken!" :
                "Invalid username or business name or password!");
    }


}
