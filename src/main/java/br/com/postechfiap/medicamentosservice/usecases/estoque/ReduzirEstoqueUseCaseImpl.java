package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.dto.estoque.request.ReduzirEstoqueDto;
import br.com.postechfiap.medicamentosservice.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.interfaces.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque.ReduzirEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReduzirEstoqueUseCaseImpl implements ReduzirEstoqueUseCase {

    private final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueResponse execute (ReduzirEstoqueDto entry){

        var estoque = estoqueRepository.findBySku(entry.sku())
                .orElseThrow(EstoqueNotFoundException::new);
        long quantidade= estoque.getQuantidade() - entry.reduzirEstoqueRequest().quantidade();
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
