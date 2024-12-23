package com.jarosinski.accounts.service.impl;

import com.jarosinski.accounts.constants.AccountConstants;
import com.jarosinski.accounts.dto.AccountDTO;
import com.jarosinski.accounts.dto.CustomerDTO;
import com.jarosinski.accounts.entity.Account;
import com.jarosinski.accounts.entity.Customer;
import com.jarosinski.accounts.exception.CustomerAlreadyExistsException;
import com.jarosinski.accounts.exception.ResourceNotFoundException;
import com.jarosinski.accounts.mapper.AccountMapper;
import com.jarosinski.accounts.mapper.CustomerMapper;
import com.jarosinski.accounts.repository.AccountRepository;
import com.jarosinski.accounts.repository.CustomerRepository;
import com.jarosinski.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

        customerOptional.ifPresent(c -> {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDTO.getMobileNumber() + " already exists");
        });

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);

        Account account = createNewAccount(savedCustomer);
        accountRepository.save(account);
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(mobileNumber);
        Customer customer = customerOptional.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId()));

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account, new AccountDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        Account account = null;
        if (accountDTO != null) {
            account = accountRepository.findById(accountDTO.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number", accountDTO.getAccountNumber()));
        }
        AccountMapper.mapToAccount(accountDTO, account);
        accountRepository.save(account);

        Long customerId = customerDTO.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", customerId));

        CustomerMapper.mapToCustomer(customerDTO, customer);
        customerRepository.save(customer);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted = false;
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(mobileNumber);
        Customer customer = customerOptional.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
        isDeleted = true;
        return isDeleted;
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + (long) (Math.random() * 9000000000L);
        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;
    }
}
