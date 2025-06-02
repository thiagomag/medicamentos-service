package br.com.postechfiap.medicamentosservice.exceptions.medicamento;

import br.com.postechfiap.medicamentosservice.exceptions.EntityNotFoundException;

public class MedicamentoNotFoundException extends EntityNotFoundException {
    public MedicamentoNotFoundException() {
        super("Estoque", "o");
    }
}
