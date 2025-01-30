package com.jarosinski.accounts.service.impl;

import com.jarosinski.accounts.dto.AccountDTO;
import com.jarosinski.accounts.dto.CustomerDetailDTO;
import com.jarosinski.accounts.entity.Account;
import com.jarosinski.accounts.entity.Customer;
import com.jarosinski.accounts.exception.ResourceNotFoundException;
import com.jarosinski.accounts.mapper.AccountMapper;
import com.jarosinski.accounts.mapper.CustomerMapper;
import com.jarosinski.accounts.repository.AccountRepository;
import com.jarosinski.accounts.repository.CustomerRepository;
import com.jarosinski.accounts.service.ICustomerService;
import com.jarosinski.accounts.service.client.CardsFeignClient;
import com.jarosinski.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private final CardsFeignClient cardsFeignClient;

    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailDTO fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId()));

        CustomerDetailDTO customerDetailDTO = CustomerMapper.mapToCustomerDetailDTO(customer, new CustomerDetailDTO());
        customerDetailDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account, new AccountDTO()));

        customerDetailDTO.setCardsDto(cardsFeignClient.fetchCardDetails(mobileNumber).getBody());
        customerDetailDTO.setLoansDto(loansFeignClient.fetchLoanDetails(mobileNumber).getBody());

        return customerDetailDTO;
    }
}
