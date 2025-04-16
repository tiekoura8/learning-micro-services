package com.tiekoura.learningmicroservices.services;

import com.tiekoura.learningmicroservices.dao.CustomerRepository;
import com.tiekoura.learningmicroservices.dto.CustomerDto;
import com.tiekoura.learningmicroservices.entities.Customer;
import com.tiekoura.learningmicroservices.exception.CustomerWithEmailAlreadyExist;
import com.tiekoura.learningmicroservices.exception.NoSuchCustomerExistsException;
import com.tiekoura.learningmicroservices.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final   CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerDto addCustomer(CustomerDto customerdto) throws CustomerWithEmailAlreadyExist {
      Customer customer = customerMapper.toEntity(customerdto);
      if(customerRepository.existsByEmail(customer.getEmail())) {
          throw new CustomerWithEmailAlreadyExist("Customer with email " + customer.getEmail() + " already exist");
      }
      return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        return customerMapper.toDto(
                customerRepository.findById(id).orElseThrow(
                        () -> new NoSuchCustomerExistsException("Customer with id " + id + " does not exist")
                )
        );
    }

    @Override
    public void updateCustomer(Long id, CustomerDto customer) {
       Customer existingCustomer = customerMapper.toEntity(this.getCustomer(id))  ;
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            customerRepository.save(existingCustomer);
        } else {
            throw new NoSuchCustomerExistsException("Customer with id " + customer.getId() + " does not exist");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existingCustomer = customerMapper.toEntity(this.getCustomer(id)) ;
        if (existingCustomer != null) {
            customerRepository.delete(existingCustomer);
        } else throw new NoSuchCustomerExistsException("Customer with id " + id + " does not exist");

    }

    @Override
    public List<CustomerDto> getCustomers() {
        return customerMapper.toDtos(customerRepository.findAll());
    }
}
