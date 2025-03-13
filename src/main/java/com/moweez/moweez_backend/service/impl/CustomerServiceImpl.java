package com.moweez.moweez_backend.service.impl;

import com.moweez.moweez_backend.dto.CustomerRequest;
import com.moweez.moweez_backend.dto.CustomerResponse;
import com.moweez.moweez_backend.entity.Customer;
import com.moweez.moweez_backend.exception.ResourceNotFoundException;
import com.moweez.moweez_backend.mapper.CustomerMapper;
import com.moweez.moweez_backend.repository.CustomerRepository;
import com.moweez.moweez_backend.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;


import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;  // Assume this mapper converts between Customer and CustomerDTO

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    /**
     * Retrieves all customers from the repository and maps them to CustomerResponse DTOs.
     *
     * @return List of CustomerResponse DTOs representing all customers.
     */
    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse createCustomer(@Valid CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, @Valid CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        // Update fields (you could also use a mapper that updates non-null properties)
        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());
        existingCustomer.setEmail(customerRequest.getEmail());
        existingCustomer.setPhone(customerRequest.getPhone());
        existingCustomer.setAddress(customerRequest.getAddress());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}