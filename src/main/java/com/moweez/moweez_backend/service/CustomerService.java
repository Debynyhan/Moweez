package com.moweez.moweez_backend.service;


import com.moweez.moweez_backend.dto.CustomerRequest;
import com.moweez.moweez_backend.dto.CustomerResponse;
import jakarta.validation.Valid;


import java.util.List;


public interface CustomerService {

    /**
     * Retrieves all customers.
     *
     * @return a list of CustomerResponse DTOs.
     */
    List<CustomerResponse> getAllCustomers();

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the customer's ID.
     * @return the CustomerResponse DTO.
     */
    CustomerResponse getCustomerById(Long id);

    /**
     * Creates a new customer.
     *
     * @param customerRequest the CustomerRequest DTO containing input data.
     * @return the created CustomerResponse DTO.
     */
    CustomerResponse createCustomer(@Valid CustomerRequest customerRequest);

    /**
     * Updates an existing customer.
     *
     * @param id the customer's ID.
     * @param customerRequest the CustomerRequest DTO containing updated data.
     * @return the updated CustomerResponse DTO.
     */
    CustomerResponse updateCustomer(Long id, @Valid CustomerRequest customerRequest);

    /**
     * Deletes a customer by their ID.
     *
     * @param id the customer's ID.
     */
    void deleteCustomer(Long id);
}