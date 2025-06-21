package br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.AdicionarEstoqueDto;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.application.interfaces.UseCase;

public interface AdicionarEstoqueUseCase extends UseCase<AdicionarEstoqueDto, EstoqueResponse> {
}
