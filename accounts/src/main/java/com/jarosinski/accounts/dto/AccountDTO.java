package com.jarosinski.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
