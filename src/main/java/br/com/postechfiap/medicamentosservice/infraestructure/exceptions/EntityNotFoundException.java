package br.com.postechfiap.medicamentosservice.infraestructure.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String genero) {
        super(entityName + " não encontrad" + genero + ".");
    }
}
