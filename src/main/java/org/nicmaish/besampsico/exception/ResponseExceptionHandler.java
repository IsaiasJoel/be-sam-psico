package org.nicmaish.besampsico.exception;

import org.nicmaish.besampsico.config.apiresponse.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleAllException(Exception ex, WebRequest request) {
        ex.printStackTrace();

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        ApiResponse apiResponse = ApiResponse.builder()
                .datetime(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .successful(false)
                .message(errors)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ex.printStackTrace();

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        ApiResponse apiResponse = ApiResponse.builder()
                .datetime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .successful(false)
                .message(errors)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ModelNotFoundExceptionCustom.class)
    public ResponseEntity<ApiResponse> handleModelNotFoundException(ModelNotFoundExceptionCustom ex, WebRequest request) {
        ex.printStackTrace();

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getId(), ex.getMessage());

        ApiResponse apiResponse = ApiResponse.builder()
                .datetime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .successful(false)
                .message(errors)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ex.printStackTrace();

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String filedName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(filedName, errorMessage);
        });

        ApiResponse apiResponse = ApiResponse.builder()
                .datetime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .successful(false)
                .message(errors)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidExceptionCustom.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidExceptionCustom(MethodArgumentNotValidExceptionCustom ex, WebRequest request) {
        ex.printStackTrace();

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getId(), ex.getMessage());

        ApiResponse apiResponse = ApiResponse.builder()
                .datetime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .successful(false)
                .message(errors)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
