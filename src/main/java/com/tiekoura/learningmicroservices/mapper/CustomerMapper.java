package com.tiekoura.learningmicroservices.mapper;


import com.tiekoura.learningmicroservices.dto.CustomerDto;
import com.tiekoura.learningmicroservices.entities.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);

    List<CustomerDto> toDtos(List<Customer> customers);
    List<Customer> toEntities(List<CustomerDto> customerDtos);
}
