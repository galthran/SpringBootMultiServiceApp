package com.jarosinski.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Error Response"
)
public class ErrorResponseDTO {


    private String apiPath;

    private String errorCode;

    private String errorMessage;

    private LocalDateTime errorTime;
}
