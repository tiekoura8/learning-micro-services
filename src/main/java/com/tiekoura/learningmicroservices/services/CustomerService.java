package com.tiekoura.learningmicroservices.services;

import com.tiekoura.learningmicroservices.entities.Customer;

import java.util.List;


public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer getCustomer(Long id);

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    List<Customer> getCustomers();
}
