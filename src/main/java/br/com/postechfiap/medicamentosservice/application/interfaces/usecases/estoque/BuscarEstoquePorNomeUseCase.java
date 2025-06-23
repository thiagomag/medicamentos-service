package br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque;


import br.com.postechfiap.medicamentosservice.application.interfaces.UseCase;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.ListaEstoqueResponse;

public interface BuscarEstoquePorNomeUseCase extends UseCase<String, ListaEstoqueResponse> {
}
