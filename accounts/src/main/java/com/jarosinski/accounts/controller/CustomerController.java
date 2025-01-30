package com.jarosinski.accounts.controller;

import com.jarosinski.accounts.constants.AccountConstants;
import com.jarosinski.accounts.dto.CustomerDetailDTO;
import com.jarosinski.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "REST API for Customers in EazyBank",
        description = "REST API's in EazyBank to Fetch customer details"
)
@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch customer details based on a mobile number"
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
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailDTO> fetchCustomerDetails(@RequestParam
                                                           @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {

        CustomerDetailDTO customerDetailDTO = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.ok(customerDetailDTO);
    }
}
