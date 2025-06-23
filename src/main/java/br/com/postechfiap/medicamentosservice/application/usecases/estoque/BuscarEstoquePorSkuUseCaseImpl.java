package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.ListaEstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.BuscarEstoquePorSkuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("buscarEstoquePorSkuUseCase")
@RequiredArgsConstructor
public class BuscarEstoquePorSkuUseCaseImpl implements BuscarEstoquePorSkuUseCase {
    private final EstoqueRepository repository;

    @Override
    public ListaEstoqueResponse execute(String query){
        List<EstoqueResponse> estoques = repository.findBySku(query)
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
