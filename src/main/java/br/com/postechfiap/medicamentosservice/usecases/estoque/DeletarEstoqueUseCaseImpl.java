package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.fiap_estoque_service.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.fiap_estoque_service.interfaces.EstoqueRepository;
import br.com.postechfiap.fiap_estoque_service.interfaces.usecases.DeletarEstoqueUseCase;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarEstoqueUseCaseImpl implements DeletarEstoqueUseCase {
    private final EstoqueRepository estoqueRepository;
    @Override
    public String execute( @NotBlank String sku) {
        var estoque =estoqueRepository.findBySku(sku)
                .orElseThrow(EstoqueNotFoundException::new);

        estoqueRepository.delete(estoque);
        return "Estoque com sku " + sku + " foi deletado com sucesso!";
    }
}
