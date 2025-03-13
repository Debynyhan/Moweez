package com.moweez.moweez_backend.service.security;

import com.moweez.moweez_backend.entity.AppUser;

import com.moweez.moweez_backend.repository.CustomerRepository;
import com.moweez.moweez_backend.repository.ServiceProviderRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository,
                                    ServiceProviderRepository serviceProviderRepository) {
        this.customerRepository = customerRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = customerRepository.findByEmail(email)
                .map(customer -> (AppUser) customer)
                .orElseGet(() -> serviceProviderRepository.findByEmail(email)
                        .map(serviceProvider -> (AppUser) serviceProvider)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email)));
        return new CustomUserDetails(appUser);
    }
}
