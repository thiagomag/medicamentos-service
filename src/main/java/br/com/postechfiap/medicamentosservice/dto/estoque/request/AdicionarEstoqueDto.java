package br.com.postechfiap.medicamentosservice.dto.estoque.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AdicionarEstoqueDto(
        @NotNull
        String sku,
        @Valid @NotNull AdicionarEstoqueRequest adicionarEstoqueRequest
) {
}

