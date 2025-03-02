package backend.config.provider;

import backend.config.CustomJwtAuthenticationToken;
import backend.config.service.JwtService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Collections;

@Component
public class CustomProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    public CustomProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Authenticating " + authentication);
        String token = (String) authentication.getCredentials();
        String username;
        String role;
        try {
            System.out.println("Begin authenticating " + authentication);
            String[] o = jwtService.validateTokenAndGetUsernameAndRole(token);
            System.out.println("Token has been validated");
            username = o[0];
            role = o[1];
        } catch (Exception e) {
            throw new AuthenticationException("No such algorithm exist or an invalid key is being used"){};
        }

        if (username == null || role == null) throw new AuthenticationException("Invalid JWT Token"){};

        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role));
        CustomJwtAuthenticationToken customJwtAuthenticationToken =  new CustomJwtAuthenticationToken(token, username, authorities);
        customJwtAuthenticationToken.setDetails(authentication.getDetails());
        System.out.println("Authenticated " + customJwtAuthenticationToken);
        return customJwtAuthenticationToken; // Successfully authenticated
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomJwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}