package com.moweez.moweez_backend.service.security;

import com.moweez.moweez_backend.entity.Customer;
import com.moweez.moweez_backend.entity.ServiceProvider;
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
        // First, try to find a customer by email.
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer != null) {
            return new CustomUserDetails(customer);
        }

        // If not found, try to find a service provider by email.
        ServiceProvider serviceProvider = serviceProviderRepository.findByEmail(email).orElse(null);
        if (serviceProvider != null) {
            return new CustomUserDetails(serviceProvider);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
