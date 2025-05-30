package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.medicamentosservice.dto.estoque.request.EstoqueRequest;
import br.com.postechfiap.medicamentosservice.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.entities.Estoque;
import br.com.postechfiap.medicamentosservice.interfaces.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque.CadastrarEstoqueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarEstoqueUseCaseImpl implements CadastrarEstoqueUseCase {

    private  final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueResponse execute (EstoqueRequest entry){
        long i = 0;
        var estoque = Estoque.builder()
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
