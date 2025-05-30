package br.com.postechfiap.medicamentosservice.usecases.estoque;

import br.com.postechfiap.medicamentosservice.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.interfaces.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.estoque.DeletarEstoqueUseCase;
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
