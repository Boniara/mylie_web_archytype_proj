package com.bavuta.controller.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bavuta.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by bogdan on 4/27/2017.
 */
public class SecuredUser implements UserDetails {

    private long id;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList();

    public SecuredUser(String username, Role role) {
        this.username = username;
        authorities.add(new SimpleGrantedAuthority(role.getRole().getValue()));
    }

    public SecuredUser(long id, String username, String password, Role role) {
        this(username, role);
        this.id = id;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
