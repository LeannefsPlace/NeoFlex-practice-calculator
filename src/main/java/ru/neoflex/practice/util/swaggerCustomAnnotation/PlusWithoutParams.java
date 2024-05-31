package ru.neoflex.practice.util.swaggerCustomAnnotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "get all sum expressions",
        description = "returns an array of expressions",
        tags = { "plus" }
)
@ApiResponse(
        responseCode = "200",
        description = "list of expressions with sum, will contain no elements if there were no sum requests",
        content = {
                @Content(mediaType = "application/json", schema =
                @Schema(description = "expression list", example = "{\"plusExpressions\": [ { \"expression\": \"50 + 50\", \"result\": 100 } ] }"))
        }
)
public @interface PlusWithoutParams {
}
