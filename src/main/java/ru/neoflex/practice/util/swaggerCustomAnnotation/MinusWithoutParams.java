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
        summary = "get all sub expressions",
        description = "returns an array of expressions"
)
@ApiResponse(
        responseCode = "200",
        description = "list of expressions with sub, will contain no elements if there were no sub requests",
        content = {
                @Content(mediaType = "application/json", schema =
                @Schema(description = "expression list", example = "{\"minusExpressions\": [ { \"expression\": \"50 - 50\", \"result\": 0 } ] }"))
        }
)
public @interface MinusWithoutParams {
}
