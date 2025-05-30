package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.AdicionarEstoqueDto;
import br.com.postechfiap.fiap_estoque_service.dto.EstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.fiap_estoque_service.interfaces.EstoqueRepository;
import br.com.postechfiap.fiap_estoque_service.interfaces.usecases.AdicionarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarEstoqueUseCaseImpl implements AdicionarEstoqueUseCase {

    private final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueResponse execute (AdicionarEstoqueDto entry){

        var estoque = estoqueRepository.findBySku(entry.sku())
                .orElseThrow(EstoqueNotFoundException::new);

        long quantidade= estoque.getQuantidade() + entry.adicionarEstoqueRequest().quantidade();
        estoque.setQuantidade(quantidade);

        estoqueRepository.save(estoque);

        return new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getSku(),
                estoque.getQuantidade()
        );
    }
}
