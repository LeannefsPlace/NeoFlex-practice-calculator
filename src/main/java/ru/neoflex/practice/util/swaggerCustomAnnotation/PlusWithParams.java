package ru.neoflex.practice.util.swaggerCustomAnnotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        summary = "get sum of elements",
        description = "additionally your expression will be added to database",
        tags = { "plus" },
        parameters = {
                @Parameter(name = "summand1", example = "50", required = true),
                @Parameter(name = "summand2", example = "50", required = true)
        }
)
@ApiResponse(
        responseCode = "200",
        description = "normally it should always return an expression element",
        content = {
                @Content(mediaType = "application/json", schema =
                @Schema(description = "expression", example = "{ \"expression\": \"50 + 50\", \"result\": 100 }"))
        }
)
@ApiResponse(
        responseCode = "400",
        description = "bad request",
        content = {
                @Content(mediaType = "application/json", schema =
                @Schema(description = "expression", example = "{ \"message\": \"unable to parse your expression\" }"))
        }
)
public @interface PlusWithParams {
}
