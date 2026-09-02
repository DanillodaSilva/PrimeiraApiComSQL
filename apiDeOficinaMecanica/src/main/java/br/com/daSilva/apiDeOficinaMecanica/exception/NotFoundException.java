package br.com.daSilva.apiDeOficinaMecanica.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
