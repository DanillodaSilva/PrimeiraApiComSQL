package br.com.daSilva.apiDeOficinaMecanica.handler;

import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestExceptionn;
import br.com.daSilva.apiDeOficinaMecanica.exception.ErrorResponse;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNorFoundException(NotFoundException notFoundException) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(notFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(BadRequestExceptionn.class)
    public ResponseEntity<ErrorResponse> handlerBadRequest(BadRequestExceptionn exceptionn) {
        ErrorResponse response = ErrorResponse.builder()
                .message(exceptionn.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
