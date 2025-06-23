package br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque;


import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.ListaEstoqueResponse;
import br.com.postechfiap.medicamentosservice.application.interfaces.UseCase;

public interface BuscarEstoquePorSkuUseCase extends UseCase<String, ListaEstoqueResponse> {
}
