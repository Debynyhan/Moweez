package com.moweez.moweez_backend.service.security;

import com.moweez.moweez_backend.entity.Customer;
import com.moweez.moweez_backend.entity.ServiceProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Object user;

    public CustomUserDetails(Object user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            return List.of(new SimpleGrantedAuthority(customer.getRole().name()));
        } else if (user instanceof ServiceProvider) {
            ServiceProvider sp = (ServiceProvider) user;
            return List.of(new SimpleGrantedAuthority(sp.getRole().name()));
        }
        return List.of();
    }

    @Override
    public String getPassword() {
        if (user instanceof Customer) {
            return ((Customer) user).getPassword();
        } else if (user instanceof ServiceProvider) {
            return ((ServiceProvider) user).getPassword();
        }
        return "";
    }

    @Override
    public String getUsername() {
        if (user instanceof Customer) {
            return ((Customer) user).getEmail();
        } else if (user instanceof ServiceProvider) {
            return ((ServiceProvider) user).getEmail();
        }
        return "";
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
        if (user instanceof Customer) {
            return ((Customer) user).isEnabled();
        } else if (user instanceof ServiceProvider) {
            return ((ServiceProvider) user).isEnabled();
        }
        return false;
    }
}
