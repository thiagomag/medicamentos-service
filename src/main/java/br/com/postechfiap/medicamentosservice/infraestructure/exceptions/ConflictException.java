package br.com.postechfiap.medicamentosservice.infraestructure.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
