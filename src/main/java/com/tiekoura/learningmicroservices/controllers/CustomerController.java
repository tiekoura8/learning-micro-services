package com.tiekoura.learningmicroservices.controllers;

import com.tiekoura.learningmicroservices.entities.Customer;
import com.tiekoura.learningmicroservices.exception.CustomerWithEmailAlreadyExist;
import com.tiekoura.learningmicroservices.exception.ErrorResponse;
import com.tiekoura.learningmicroservices.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping()
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }



    @ExceptionHandler(CustomerWithEmailAlreadyExist.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExists(CustomerWithEmailAlreadyExist ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }
}


