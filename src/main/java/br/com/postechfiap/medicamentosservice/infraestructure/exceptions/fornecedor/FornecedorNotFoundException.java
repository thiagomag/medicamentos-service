package br.com.postechfiap.medicamentosservice.infraestructure.exceptions.fornecedor;

import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.ConflictException;

public class FornecedorNotFoundException extends ConflictException {

    public FornecedorNotFoundException(Long id) { super("Fornecedor com ID: " + id + " nao encontrado."); }
}
