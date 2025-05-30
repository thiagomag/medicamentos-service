package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.request.AtualizarEstoqueDto;
import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.interfaces.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque.AtualizarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarEstoqueUseCaseImpl implements AtualizarEstoqueUseCase {

    private  final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueResponse execute (AtualizarEstoqueDto entry){

        var estoque = estoqueRepository.findBySku(entry.sku())
                .orElseThrow(EstoqueNotFoundException::new);

        estoque.setNome(entry.estoqueRequest().nome());
        estoque.setSku(entry.estoqueRequest().sku());
        estoque.setQuantidade(entry.estoqueRequest().quantidade());

        estoque = estoqueRepository.save(estoque);

        return  new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getSku(),
                estoque.getQuantidade()
        );
    }
}
