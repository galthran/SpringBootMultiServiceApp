package com.jarosinski.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Customer",
        description = "Customer details"
)
public class CustomerDTO {

    private Long customerId;

    @Schema(
        description = "Name of the customer",
        example = "John Doe"
    )
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Schema(
        description = "Email of the customer",
        example = "0q4QI@example.com"
    )
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(
        description = "Mobile number of the customer",
        example = "1234567890"
    )
    @NotEmpty(message = "Mobile number cannot be empty")
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Mobile number must contain only digits")
    private String mobileNumber;

    @Schema(
            name = "Account",
            description = "Account details of the customer"
    )
    private AccountDTO accountDTO;
}
