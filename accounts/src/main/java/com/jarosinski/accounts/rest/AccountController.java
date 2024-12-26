package com.jarosinski.accounts.rest;

import com.jarosinski.accounts.constants.AccountConstants;
import com.jarosinski.accounts.dto.CustomerDTO;
import com.jarosinski.accounts.dto.ResponseDTO;
import com.jarosinski.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Accounts",
    description = "Operations related to accounts"
)
@RestController
@RequestMapping(value = "/api", produces = "application/json")
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Create account",
            description = "Create account for a customer"
    )
    @ApiResponse(
            responseCode = AccountConstants.STATUS_201,
            description = AccountConstants.MESSAGE_201
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        iAccountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch account",
            description = "Fetch account details for a customer"
    )
    @ApiResponse(
            responseCode = AccountConstants.STATUS_200,
            description = AccountConstants.MESSAGE_200
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetail(@RequestParam
                                                          @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
                                                          String mobileNumber) {
        CustomerDTO customerDTO = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @Operation(
            summary = "Update account",
            description = "Update account details for a customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_200,
                    description = AccountConstants.MESSAGE_200
            ),
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_500,
                    description = AccountConstants.MESSAGE_500
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetail(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = iAccountService.updateAccount(customerDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetail(@RequestParam
                                                           @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
