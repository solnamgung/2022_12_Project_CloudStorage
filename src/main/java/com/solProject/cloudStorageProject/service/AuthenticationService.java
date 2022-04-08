package com.solProject.cloudStorageProject.service;

import com.solProject.cloudStorageProject.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserService userService;
    private HashService hashService;

    public AuthenticationService(UserService userService, HashService hashService) {
        this.userService = userService;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User currentUser = userService.getUser(username);

        if(currentUser != null) {
            String encodeSalt = currentUser.getSalt();
            String hashedPassword = hashService.getHashedValue(currentUser.getPassword(), encodeSalt);
            if(currentUser.getPassword().equals(hashedPassword))
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
