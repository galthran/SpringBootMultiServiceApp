package com.jarosinski.accounts.mapper;

import com.jarosinski.accounts.dto.CustomerDTO;
import com.jarosinski.accounts.dto.CustomerDetailDTO;
import com.jarosinski.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static CustomerDetailDTO mapToCustomerDetailDTO(Customer customer, CustomerDetailDTO customerDetailDTO) {
        customerDetailDTO.setName(customer.getName());
        customerDetailDTO.setEmail(customer.getEmail());
        customerDetailDTO.setMobileNumber(customer.getMobileNumber());
        return customerDetailDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
