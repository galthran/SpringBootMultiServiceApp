package com.jarosinski.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;

    private AccountDTO accountDTO;
}
