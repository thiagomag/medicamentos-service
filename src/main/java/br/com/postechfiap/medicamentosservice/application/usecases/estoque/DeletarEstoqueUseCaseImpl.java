package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.DeletarEstoqueUseCase;
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

        estoque.delete();
        estoqueRepository.save(estoque);
        return sku;
    }
}
