package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;


import br.com.postechfiap.medicamentosservice.dto.estoque.request.EstoqueRequest;
import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;

public interface CadastrarEstoqueUseCase extends UseCase<EstoqueRequest, EstoqueResponse> {
}
