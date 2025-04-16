package com.tiekoura.learningmicroservices.services;

import com.tiekoura.learningmicroservices.dto.CustomerDto;

import java.util.List;


public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customer);

    CustomerDto getCustomer(Long id);

    void updateCustomer(Long id , CustomerDto customer);

    void deleteCustomer(Long id);

    List<CustomerDto> getCustomers();
}
