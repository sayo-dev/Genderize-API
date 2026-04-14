package org.example.genderize.exception;

import jakarta.validation.ConstraintViolationException;
import org.example.genderize.util.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(CustomBadRequestException ex) {

        return ResponseEntity.badRequest().body(ApiResponse.error(ex.getMessage()));

    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ApiResponse<String>> handleUnprocessableEntityException(UnprocessableEntityException ex) {

        return ResponseEntity.unprocessableContent().body(ApiResponse.error(ex.getMessage()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors().stream().map(
                        DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Validation failed");

        return ResponseEntity.badRequest().body(ApiResponse.error(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations().iterator().next().getMessage();

        return ResponseEntity.badRequest().body(ApiResponse.error(message));

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Invalid value for parameter '%s'", ex.getName());
        return ResponseEntity.badRequest().body(ApiResponse.error(message));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiResponse<String>> handleRestClientException(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ApiResponse.error("External API error: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleInternalServerError(Exception ex) {
        return ResponseEntity.internalServerError().body(ApiResponse.error("An error occurred: " + ex.getMessage()));
    }
}
