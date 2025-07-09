package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.EstoqueRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.CadastrarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarEstoqueUseCaseImpl implements CadastrarEstoqueUseCase {

    private  final EstoqueGateway estoqueGateway;

    @Override
    public EstoqueResponse execute (EstoqueRequest entry){

        var estoque = EstoqueEntity.builder()
                .nome(entry.nome())
                .sku(entry.sku())
                .quantidade(entry.estoque())
                .build();

        estoque = estoqueGateway.save(estoque);

        return  new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getSku(),
                estoque.getQuantidade(),
                estoque.getReposicaoPendente()
        );
    }
}
