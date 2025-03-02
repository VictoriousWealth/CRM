package backend.User;

import backend.Registration.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {
    /**
     * @param user the user to check
     * this method returns true if the user's details provided are valid
     * */
    boolean validate(UserDTO user);
    /**
     * @param user the user to check
     * this method returns true if the user's username is unique and not already in the database
     * */
    boolean available(UserDTO user);

    /**
     * @param user the user that needs to be registered
     *
     * */
    boolean register(UserDTO user);
}
