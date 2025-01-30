package com.jarosinski.accounts.service;

import com.jarosinski.accounts.dto.CustomerDetailDTO;

public interface ICustomerService {

    CustomerDetailDTO fetchCustomerDetails(String mobileNumber);

}
