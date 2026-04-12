package org.example.genderize.exception;

import jakarta.validation.ConstraintViolationException;
import org.example.genderize.util.ApiResponse;
import org.springframework.http.ResponseEntity;
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
                        fieldError -> fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return ResponseEntity.badRequest().body(ApiResponse.error(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations().iterator().next().getMessage();

        return ResponseEntity.badRequest().body(ApiResponse.error(message));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleInternalServerError(Exception ex) {

        System.out.println(ex.getClass());
        return ResponseEntity.internalServerError().body(ApiResponse.error(ex.getMessage()));
    }
}
