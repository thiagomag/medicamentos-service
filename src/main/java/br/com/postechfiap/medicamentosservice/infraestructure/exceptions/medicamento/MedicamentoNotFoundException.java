package br.com.postechfiap.medicamentosservice.infraestructure.exceptions.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.EntityNotFoundException;

public class MedicamentoNotFoundException extends EntityNotFoundException {
    public MedicamentoNotFoundException() {
        super("Estoque", "o");
    }
}
