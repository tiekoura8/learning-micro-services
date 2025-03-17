package com.tiekoura.learningmicroservices.services;

import com.tiekoura.learningmicroservices.dao.CustomerRepository;
import com.tiekoura.learningmicroservices.entities.Customer;
import com.tiekoura.learningmicroservices.exception.CustomerWithEmailAlreadyExist;
import com.tiekoura.learningmicroservices.exception.NoSuchCustomerExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final   CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer addCustomer(Customer customer) throws CustomerWithEmailAlreadyExist {
        Customer existingCustomer = customerRepository.findByEmail(customer.getEmail());
        if (existingCustomer == null) {
            return customerRepository.save(customer);
        } else {
            throw new CustomerWithEmailAlreadyExist("Customer with email " + customer.getEmail() + " already exist");
        }
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NoSuchCustomerExistsException("Customer with id " + id + " does not exist")
        );
    }

    @Override
    public void updateCustomer(Customer customer) {
        Customer existingCustomer = this.getCustomer(customer.getId());
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());


        }
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
