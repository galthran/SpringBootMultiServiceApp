package com.jarosinski.accounts.service;

import com.jarosinski.accounts.dto.CustomerDTO;

public interface IAccountService {

    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}
