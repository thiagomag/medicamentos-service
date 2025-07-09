package br.com.postechfiap.medicamentosservice.application.usecases.estoque;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.AtualizarReposicaoPendenteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarReposicaoPendenteUseCaseImpl implements AtualizarReposicaoPendenteUseCase {

    private final EstoqueGateway estoqueGateway;

    @Override
    public Void execute(String sku) {
        final var estoque = estoqueGateway.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Estoque not found for SKU: " + sku));
        estoque.setReposicaoPendente(Boolean.TRUE);
        estoqueGateway.update(estoque);
        return null;
    }
}
