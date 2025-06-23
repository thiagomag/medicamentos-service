package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.BuscarEstoquePorNomeUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.BuscarEstoquePorSkuUseCase;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.ListaEstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("buscarEstoquePorNomeUseCase")
@RequiredArgsConstructor
public class BuscarEstoquePorNomeUseCaseImpl implements BuscarEstoquePorNomeUseCase {
    private final EstoqueRepository repository;

    @Override
    public ListaEstoqueResponse execute(String query){
        List<EstoqueResponse> estoques = repository.findByNomeContainingIgnoreCase(query)
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
