package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.AlterarEstoqueRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueInsuficienteException;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.ReduzirEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReduzirEstoqueUseCaseImpl implements ReduzirEstoqueUseCase {

    private final EstoqueGateway estoqueGateway;

    @Override
    public EstoqueResponse execute (AlterarEstoqueRequest entry){

        var estoque = estoqueGateway.findBySku(entry.sku())
                .orElseThrow(EstoqueNotFoundException::new);

        if(estoque.getQuantidade() < entry.quantidade()){
            throw new EstoqueInsuficienteException("Estoque de Remoção ultrapassa o estoque atual de: "
                    + estoque.getQuantidade());
        }

        int quantidade= estoque.getQuantidade() - entry.quantidade();
        estoque.setQuantidade(quantidade);

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
