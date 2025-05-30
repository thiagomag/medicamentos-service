package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.dto.estoque.request.ReduzirEstoqueDto;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;

public interface ReduzirEstoqueUseCase extends UseCase<ReduzirEstoqueDto, EstoqueResponse> {
}
