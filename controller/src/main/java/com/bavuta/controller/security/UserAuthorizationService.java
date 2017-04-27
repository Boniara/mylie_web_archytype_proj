package com.bavuta.controller.security;

import com.bavuta.model.User;
import com.bavuta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by bogdan on 4/27/2017.
 */
public class UserAuthorizationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByUsername(username);
            if (user == null) {
                throw new Exception("Invalid user name " + username);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found", e);
        }
        return new SecuredUser(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
