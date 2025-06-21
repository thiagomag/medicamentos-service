package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.EstoqueRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.CadastrarEstoqueUseCase;
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
