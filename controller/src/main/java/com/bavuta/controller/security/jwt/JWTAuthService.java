package com.bavuta.controller.security.jwt;

import com.bavuta.controller.security.SecuredUser;
import com.bavuta.model.User;
import com.bavuta.service.auth.JWTService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthService implements UserDetailsService {

    @Autowired
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        User user = null;
        try {
            user = jwtService.parseAuthToken(token);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found", e);
        }
        return new SecuredUser(user.getId(), user.getUsername(), StringUtils.EMPTY, user.getRole());
    }
}