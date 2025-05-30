package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.request.AtualizarEstoqueDto;
import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;

public interface AtualizarEstoqueUseCase extends UseCase<AtualizarEstoqueDto,EstoqueResponse> {
}
