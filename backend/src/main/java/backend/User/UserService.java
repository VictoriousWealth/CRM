package backend.User;

import backend.Registration.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(UserDTO user) {
        boolean success = user != null &&
                user.getUsername() != null &&
                user.getPassword() != null &&
                user.getRole() != null &&

                !user.getUsername().isBlank() &&
                !user.getPassword().isBlank() &&
                !user.getRole().isBlank() &&

                List.of(Role.ADMIN.getName(), Role.VENDOR.getName(), Role.CUSTOMER.getName()).contains(user.getRole().toUpperCase())
                ;
        return success;
    }

    @Override
    public boolean available(UserDTO user) {
        return userRepository.findByEmail(user.getUsername()).isEmpty();
    }

    @Override
    public boolean register(UserDTO user) {
        User actualUser = new User();
        actualUser.setEmail(user.getUsername());
        actualUser.setPassword(user.getPassword());
        actualUser.setRole(Role.valueOf(user.getRole().toUpperCase()));
        userRepository.save(actualUser);
        return true;
    }

}
