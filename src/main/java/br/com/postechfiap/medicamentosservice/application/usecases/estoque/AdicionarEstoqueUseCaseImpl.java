package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.AlterarEstoqueRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.AdicionarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarEstoqueUseCaseImpl implements AdicionarEstoqueUseCase {

    private final EstoqueGateway estoqueGateway;

    @Override
    public EstoqueResponse execute (AlterarEstoqueRequest entry){

        var estoque = estoqueGateway.findBySku(entry.sku())
                .orElseThrow(EstoqueNotFoundException::new);

        int quantidade= estoque.getQuantidade() + entry.quantidade();

        estoque.setQuantidade(quantidade);
        estoque.setReposicaoPendente(Boolean.FALSE);

        estoqueGateway.save(estoque);

        return new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getSku(),
                estoque.getQuantidade(),
                estoque.getReposicaoPendente()
        );
    }
}
