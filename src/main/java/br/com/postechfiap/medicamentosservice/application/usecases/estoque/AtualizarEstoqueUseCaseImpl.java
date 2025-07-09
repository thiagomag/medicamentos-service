package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.AtualizarEstoqueDto;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.AtualizarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarEstoqueUseCaseImpl implements AtualizarEstoqueUseCase {

    private final EstoqueGateway estoqueGateway;

    @Transactional
    @Override
    public EstoqueResponse execute (AtualizarEstoqueDto entry){

        var estoque = estoqueGateway.findBySku(entry.skuAntigo())
                .orElseThrow(EstoqueNotFoundException::new);

        estoque.setNome(entry.nome());
        estoque.setSku(entry.skuNovo());
        //estoque.setQuantidade(entry.estoqueRequest().estoque());

        estoqueGateway.save(estoque);

        return  new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getSku(),
                estoque.getQuantidade(),
                estoque.getReposicaoPendente()
        );
    }
}
