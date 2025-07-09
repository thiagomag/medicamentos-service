package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque.EstoqueNotFoundException;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.DeletarEstoqueUseCase;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarEstoqueUseCaseImpl implements DeletarEstoqueUseCase {

    private final EstoqueGateway estoqueGateway;
    @Override
    public String execute( @NotBlank String sku) {
        var estoque = estoqueGateway.findBySku(sku)
                .orElseThrow(EstoqueNotFoundException::new);
        estoqueGateway.delete(estoque);
        return sku;
    }
}
