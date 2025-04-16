package com.tiekoura.learningmicroservices.controllers;

import com.tiekoura.learningmicroservices.dto.CustomerDto;
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
    public CustomerDto createCustomer(@RequestBody CustomerDto customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping()
    public List<CustomerDto> getAllCustomers() {
        return customerService.getCustomers();
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customer) {
         customerService.updateCustomer(id,customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


    @ExceptionHandler(CustomerWithEmailAlreadyExist.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExists(CustomerWithEmailAlreadyExist ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }
}


