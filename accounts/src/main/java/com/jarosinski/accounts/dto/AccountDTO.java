package com.jarosinski.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Account",
        description = "Account details"
)
public class AccountDTO {

    @Schema(
            name = "Account Number",
            description = "Account number"
    )
    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(
            name = "Account Type",
            description = "Account type",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type cannot be empty")
    private String accountType;

    @Schema(
            name = "Branch Address",
            description = "Branch address",
            example = "123 Main Street, New York"
    )
    @NotEmpty(message = "Branch Address cannot be empty")
    private String branchAddress;
}
