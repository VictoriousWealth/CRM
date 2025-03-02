package backend.Registration;

import backend.User.Role;
import backend.User.User;
import backend.User.UserRepository;
import backend.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
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


}
