package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.dto.EstoqueRequest;
import br.com.postechfiap.fiap_estoque_service.dto.EstoqueResponse;
import br.com.postechfiap.fiap_estoque_service.entities.EstoqueEntity;
import br.com.postechfiap.fiap_estoque_service.interfaces.EstoqueRepository;
import br.com.postechfiap.fiap_estoque_service.interfaces.usecases.CadastrarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarEstoqueUseCaseImpl implements CadastrarEstoqueUseCase {

    private  final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueResponse execute (EstoqueRequest entry){
        long i = 0;
        var estoque = EstoqueEntity.builder()
                .nome(entry.nome())
                .sku(entry.sku())
                .quantidade(i)
                .build();

        estoque = estoqueRepository.save(estoque);

        return  new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getSku(),
                estoque.getQuantidade()
        );
    }
}
