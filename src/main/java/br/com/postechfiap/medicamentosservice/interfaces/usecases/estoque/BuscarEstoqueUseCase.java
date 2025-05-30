package br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.ListaEstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.interfaces.UseCase;

public interface BuscarEstoqueUseCase extends UseCase<String, ListaEstoqueResponse> {
}
