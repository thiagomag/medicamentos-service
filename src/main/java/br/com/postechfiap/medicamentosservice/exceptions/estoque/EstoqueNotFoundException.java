package br.com.postechfiap.medicamentosservice.exceptions.estoque;

import br.com.postechfiap.fiap_estoque_service.exceptions.EntityNotFoundException;

public class EstoqueNotFoundException extends EntityNotFoundException {
    public EstoqueNotFoundException() {
        super("Estoque", "o");
    }
}
