package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.EstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.dto.ReduzirEstoqueDto;
import br.com.postechfiap.fiap_estoque_service.interfaces.UseCase;

public interface ReduzirEstoqueUseCase extends UseCase<ReduzirEstoqueDto, EstoqueResponse> {
}
