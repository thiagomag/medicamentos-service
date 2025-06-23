package br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque;

import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.EntityNotFoundException;

public class EstoqueNotFoundException extends EntityNotFoundException {
    public EstoqueNotFoundException() {
        super("Estoque", "o");
    }
}
