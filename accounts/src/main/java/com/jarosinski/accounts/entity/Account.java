package com.jarosinski.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account extends BaseEntity {

    public Account(Long accountNumber, String accountType, String branchAddress) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }


    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "account_number", length = 100, nullable = false)
    private Long accountNumber;

    @Column(name = "account_type", length = 100, nullable = false)
    private String accountType;

    @Column(name = "branch_address", length = 200, nullable = false)
    private String branchAddress;
}
