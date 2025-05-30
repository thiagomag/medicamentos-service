package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;


import br.com.postechfiap.medicamentosservice.dto.estoque.response.ListaEstoqueResponse;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;

public interface BuscarEstoqueUseCase extends UseCase<String, ListaEstoqueResponse> {
}
