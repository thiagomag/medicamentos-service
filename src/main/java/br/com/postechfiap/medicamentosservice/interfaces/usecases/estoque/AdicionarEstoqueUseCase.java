package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.request.AdicionarEstoqueDto;
import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;

public interface AdicionarEstoqueUseCase extends UseCase<AdicionarEstoqueDto, EstoqueResponse> {
}
