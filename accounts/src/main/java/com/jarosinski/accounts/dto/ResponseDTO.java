package com.jarosinski.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
    name = "ResponseDTO",
    description = "Response DTO"
)
public class ResponseDTO {

    @Schema(
        name = "Status Code",
        description = "Status code of the response",
        example = "200"
    )
    private String statusCode;

    @Schema(
        name = "Status Message",
        description = "Status message of the response",
        example = "Success"
    )
    private String statusMsg;

}
