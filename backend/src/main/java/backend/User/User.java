package backend.User;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "CRM_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder(12).encode(password);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Role> getRoles() {
        return Collections.singleton(role);
    }

}