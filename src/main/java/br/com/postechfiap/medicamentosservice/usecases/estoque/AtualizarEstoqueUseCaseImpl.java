package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.AtualizarEstoqueDto;
import br.com.postechfiap.fiap_estoque_service.dto.EstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.fiap_estoque_service.interfaces.EstoqueRepository;
import br.com.postechfiap.fiap_estoque_service.interfaces.usecases.AtualizarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarEstoqueUseCaseImpl implements AtualizarEstoqueUseCase {

    private  final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueResponse execute (AtualizarEstoqueDto entry){
        long i = 0;

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
