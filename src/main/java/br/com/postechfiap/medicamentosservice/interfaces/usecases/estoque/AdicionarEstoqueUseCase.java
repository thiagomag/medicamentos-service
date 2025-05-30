package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.AdicionarEstoqueDto;
import br.com.postechfiap.fiap_estoque_service.dto.EstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.interfaces.UseCase;

public interface AdicionarEstoqueUseCase extends UseCase<AdicionarEstoqueDto, EstoqueResponse> {
}
