package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.request.AdicionarEstoqueDto;
import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.AdicionarEstoqueUseCase;
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
