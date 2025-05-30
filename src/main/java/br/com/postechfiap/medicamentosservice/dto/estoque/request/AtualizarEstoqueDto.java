package br.com.postechfiap.medicamentosservice.dto.estoque.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AtualizarEstoqueDto(
    @NotNull String sku,
    @Valid @NotNull EstoqueRequest estoqueRequest
) {
}
