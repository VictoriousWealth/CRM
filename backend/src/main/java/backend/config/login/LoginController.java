package backend.config.login;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<String> home() {
        //TODO return customer's name, and jwt from security context
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("Hello " + username + "! Here's your JWT: " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
    }
}
