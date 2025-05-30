package br.com.postechfiap.medicamentosservice.exceptions.estoque;

import br.com.postechfiap.medicamentosservice.exceptions.EntityNotFoundException;

public class EstoqueNotFoundException extends EntityNotFoundException {
    public EstoqueNotFoundException() {
        super("Estoque", "o");
    }
}
