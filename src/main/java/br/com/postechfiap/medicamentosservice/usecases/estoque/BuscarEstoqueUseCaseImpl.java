package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.EstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.dto.ListaEstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.interfaces.EstoqueRepository;
import br.com.postechfiap.fiap_estoque_service.interfaces.usecases.BuscarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarEstoqueUseCaseImpl implements BuscarEstoqueUseCase {
    private final EstoqueRepository repository;

    @Override
    public ListaEstoqueResponse execute(String query){
        List<EstoqueResponse> estoques = repository.findByNomeContainingIgnoreCaseOrSkuIgnoreCase(query,query)
                .stream()
                .map(
                        estoqueEntity -> new EstoqueResponse(
                                estoqueEntity.getId(),
                                estoqueEntity.getNome(),
                                estoqueEntity.getSku(),
                                estoqueEntity.getQuantidade()
                        )
                ).collect(Collectors.toList());
        return new ListaEstoqueResponse(estoques);
    }
}
